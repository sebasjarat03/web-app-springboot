package com.example.demo.test_Dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import com.example.demo.Taller1Application;
import com.example.demo.dao.IPurchaseOrderDetailDao;
import com.example.demo.model.prchasing.Purchaseorderdetail;

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
public class PurchaseOrderDetailTest {
    @Autowired
    private IPurchaseOrderDetailDao podDao;

    private Purchaseorderdetail pod;

    @Autowired
    public PurchaseOrderDetailTest(IPurchaseOrderDetailDao podDao) {
        this.podDao = podDao;
    }

    public void createPOD() {
        pod = new Purchaseorderdetail();
        pod.setProductid(5);
        pod.setUnitprice(16.0);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(1)
    @Test
    public void savePODTest() {
        createPOD();
        podDao.save(pod);
        // 2 porque ya hay un vendedor guardado desde el commandlinerunner
        assertEquals(2, podDao.findAll().size());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(2)
    @Test
    public void updatePODTest() {
        Purchaseorderdetail pod1 = new Purchaseorderdetail();
        pod1.setProductid(9);
        pod1.setUnitprice(8.0);
        podDao.save(pod1);

        Purchaseorderdetail podCopy = podDao.findById(3);
        podCopy.setProductid(6);
        podDao.update(podCopy);
        assertEquals(6, podDao.findById(3).getProductid());

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(3)
    @Test
    public void findAllTest() {
        assertEquals(3, podDao.findAll().size());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(4)
    @Test
    public void deleteTest() {
        Purchaseorderdetail toDel = podDao.findById(1);
        podDao.delete(toDel);
        assertEquals(2, podDao.findAll().size());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(5)
    @Test
    public void findByProductId() {
        Purchaseorderdetail toComp = podDao.findById(2);
        assertEquals(toComp, podDao.findByProductId(5).get(0));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(5)
    @Test
    public void findByUnitPrice() {
        Purchaseorderdetail toComp = podDao.findById(2);
        assertEquals(toComp, podDao.findByUnitPrice(16.0).get(0));
    }

}
