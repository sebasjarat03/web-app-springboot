package com.example.demo.test_Dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.Taller1Application;
import com.example.demo.dao.IPurchaseOrderHeaderDao;
import com.example.demo.dao.IShipMethodDao;
import com.example.demo.dao.IVendorDao;
import com.example.demo.model.person.Businessentity;
import com.example.demo.model.prchasing.Purchaseorderheader;
import com.example.demo.model.prchasing.Shipmethod;
import com.example.demo.model.prchasing.Vendor;
import com.example.demo.repositories.BusinessEntityRepository;

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
public class PurchaseOrderHeaderDaoTest {

    @Autowired
    private IPurchaseOrderHeaderDao pohDao;

    @Autowired
    private IVendorDao vDao;

    @Autowired
    private IShipMethodDao smDao;

    @Autowired
    private BusinessEntityRepository ber;

    private Purchaseorderheader poh;

    private Shipmethod sm;

    private Vendor v;

    private Businessentity be;

    @Autowired
    public PurchaseOrderHeaderDaoTest(IPurchaseOrderHeaderDao pohDao, IVendorDao vDao, IShipMethodDao smDao,
            BusinessEntityRepository ber) {
        this.pohDao = pohDao;
        this.vDao = vDao;
        this.smDao = smDao;
        this.ber = ber;
    }

    public void createPOH() {
        poh = new Purchaseorderheader();
        v = new Vendor();
        sm = new Shipmethod();
        be = new Businessentity();
        sm.setName("sm1");
        ber.save(be);

        v.setBusinessentityid(be.getBusinessentityid());
        vDao.save(v);
        sm = smDao.save(sm);
        poh.setVendor(v);
        poh.setShipmethod(sm);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(1)
    @Test
    public void savePOHTest() {
        createPOH();
        pohDao.save(poh);
        // 2 porque ya hay un vendedor guardado desde el commandlinerunner
        assertEquals(2, pohDao.findAll().size());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(2)
    @Test
    public void updatePOHTest() {
        Purchaseorderheader poh1 = new Purchaseorderheader();
        Vendor v1 = new Vendor();
        Shipmethod sm1 = new Shipmethod();
        Businessentity be1 = new Businessentity();
        sm1.setName("sm1");
        ber.save(be1);

        v1.setBusinessentityid(be1.getBusinessentityid());
        vDao.save(v1);
        Shipmethod smx = smDao.save(sm1);
        poh1.setVendor(v1);
        poh1.setShipmethod(smx);

        pohDao.save(poh1);

        Purchaseorderheader pohCopy = pohDao.findById(3);
        Shipmethod smnew = new Shipmethod();
        smnew.setName("sm Nuevo");
        smnew = smDao.save(smnew);
        pohCopy.setShipmethod(smnew);
        pohDao.update(pohCopy);
        assertEquals("sm Nuevo", pohDao.findById(3).getShipmethod().getName());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(3)
    @Test
    public void findAllTest() {
        assertEquals(3, pohDao.findAll().size());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(4)
    @Test
    public void deleteTest() {
        Purchaseorderheader toDel = pohDao.findById(3);
        pohDao.delete(toDel);
        assertEquals(2, pohDao.findAll().size());

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(5)
    @Test
    public void findByShipmethodIdTest() {
        Purchaseorderheader poht = pohDao.findById(2);
        assertEquals(poht, pohDao.findByShipMethodId(2).get(0));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(5)
    @Test
    public void findByVendorIdTest() {
        Purchaseorderheader toComp = pohDao.findById(2);
        assertEquals(toComp, pohDao.findByVendorId(2).get(0));
    }

}
