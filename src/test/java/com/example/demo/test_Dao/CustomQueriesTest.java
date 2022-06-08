package com.example.demo.test_Dao;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Taller1Application;
import com.example.demo.dao.ICustomQueriesDao;
import com.example.demo.dao.IPurchaseOrderDetailDao;
import com.example.demo.dao.IPurchaseOrderHeaderDao;
import com.example.demo.model.prchasing.Purchaseorderdetail;
import com.example.demo.model.prchasing.Purchaseorderheader;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Taller1Application.class)
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class CustomQueriesTest {
    @Autowired
    private ICustomQueriesDao qDao;

    @Autowired
    private IPurchaseOrderHeaderDao pohDao;

    @Autowired
    private IPurchaseOrderDetailDao podDao;

    @Autowired
    public CustomQueriesTest(ICustomQueriesDao qDao, IPurchaseOrderHeaderDao pohDao, IPurchaseOrderDetailDao podDao) {
        this.qDao = qDao;
        this.pohDao = pohDao;
        this.podDao = podDao;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(1)
    @Test
    public void testByDate() {
        Purchaseorderheader poh1 = new Purchaseorderheader();
        poh1.setOrderdate(LocalDate.parse("2022-03-21"));
        Purchaseorderdetail pod1 = new Purchaseorderdetail();
        pod1.setUnitprice(15.0);
        Purchaseorderdetail pod2 = new Purchaseorderdetail();
        pod2.setUnitprice(20.0);
        podDao.save(pod1);
        podDao.save(pod2);
        pod1 = podDao.findById(2);
        pod2 = podDao.findById(3);
        List<Purchaseorderdetail> podList = new ArrayList<Purchaseorderdetail>();
        podList.add(pod1);
        podList.add(pod2);
        poh1.setPurchaseorderdetails(podList);
        pohDao.save(poh1);
        poh1 = pohDao.findById(2);
        pod1.setPurchaseorderheader(poh1);
        pod2.setPurchaseorderheader(poh1);
        podDao.update(pod1);
        podDao.update(pod2);

        LocalDate date1 = LocalDate.parse("2022-01-02");
        LocalDate date2 = LocalDate.parse("2022-05-12");
        List<Object[]> rList = qDao.findPurchaseOrderHeaderByDate(date1, date2);
        Purchaseorderheader pohT = (Purchaseorderheader) rList.get(0)[0];
        Double sumR = (Double) rList.get(0)[1];
        assertAll(() -> assertEquals(1, rList.size()),
                () -> assertEquals(2, pohT.getPurchaseorderid()),
                () -> assertEquals(35.0, sumR));

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(2)
    @Test
    public void testAtLeast2() {
        Purchaseorderheader poh1 = new Purchaseorderheader();
        Purchaseorderdetail pod1 = new Purchaseorderdetail();
        Purchaseorderdetail pod2 = new Purchaseorderdetail();
        podDao.save(pod1);
        podDao.save(pod2);
        pod1 = podDao.findById(4);
        pod2 = podDao.findById(5);
        List<Purchaseorderdetail> podList = new ArrayList<Purchaseorderdetail>();
        podList.add(pod1);
        podList.add(pod2);
        poh1.setPurchaseorderdetails(podList);
        pohDao.save(poh1);
        poh1 = pohDao.findById(3);
        pod1.setPurchaseorderheader(poh1);
        pod2.setPurchaseorderheader(poh1);
        podDao.update(pod1);
        podDao.update(pod2);

        Purchaseorderheader poh2 = new Purchaseorderheader();
        Purchaseorderdetail pod3 = new Purchaseorderdetail();
        podDao.save(pod3);
        pod3 = podDao.findById(6);
        List<Purchaseorderdetail> podList2 = new ArrayList<Purchaseorderdetail>();
        podList2.add(pod3);
        poh2.setPurchaseorderdetails(podList2);
        pohDao.save(poh2);
        poh2 = pohDao.findById(4);
        pod3.setPurchaseorderheader(poh2);
        podDao.update(pod3);

        List<Purchaseorderheader> listResult = qDao.findByLeast2();

        assertAll(() -> assertEquals(4, pohDao.findAll().size()),
                () -> assertEquals(2, listResult.size()),
                () -> assertEquals(2, listResult.get(0).getPurchaseorderid()),
                () -> assertEquals(3, listResult.get(1).getPurchaseorderid()));

    }

}
