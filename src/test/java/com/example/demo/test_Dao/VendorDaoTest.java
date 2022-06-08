package com.example.demo.test_Dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.demo.Taller1Application;
import com.example.demo.dao.IVendorDao;
import com.example.demo.model.person.Businessentity;
import com.example.demo.model.prchasing.Vendor;
import com.example.demo.repositories.BusinessEntityRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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
public class VendorDaoTest {

    @Autowired
    private IVendorDao vendorDao;

    @Autowired
    private BusinessEntityRepository ber;

    private Vendor vendor;

    private Businessentity be;

    @Autowired
    public VendorDaoTest(IVendorDao vendorDao, BusinessEntityRepository ber) {
        this.vendorDao = vendorDao;
        this.ber = ber;
    }

    public void createVendor() {
        be = new Businessentity();
        ber.save(be);
        vendor = new Vendor();
        vendor.setBusinessentityid(be.getBusinessentityid());
        vendor.setName("Carlos");
        vendor.setCreditrating(15);
        vendor.setPreferredvendorstatus("active");

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Test
    public void saveVendorTest() {

        createVendor();
        vendorDao.save(vendor);
        // 2 porque ya hay un vendedor guardado desde el commandlinerunner
        assertEquals(2, vendorDao.findAll().size());

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Test
    public void updateVendorTest() {
        Businessentity be1 = new Businessentity();
        ber.save(be1);
        Vendor v = new Vendor();
        v.setBusinessentityid(be1.getBusinessentityid());
        v.setName("Fernando");
        v.setCreditrating(18);
        v.setPreferredvendorstatus("active");
        v.setName("Sebastian");
        vendorDao.save(v);
        v.setName("Sebastian");
        vendorDao.update(v);
        assertEquals(2, vendorDao.findByName("Sebastian").size());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Test
    public void findByNameTest() {
        assertFalse(vendorDao.findByName("Sebastian").isEmpty());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Test
    public void findByCreditRateTest() {
        assertFalse(vendorDao.findByCreditRate(15).isEmpty());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Test
    public void findByPreferStatTest() {
        assertFalse(vendorDao.findByPreferredStat("active").isEmpty());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Test
    public void findAllTest() {
        assertEquals(3, vendorDao.findAll().size());

    }

    @Transactional
    @Test
    public void deleteVendorTest() {
        createVendor();
        vendorDao.delete(vendor);
        assertEquals(2, vendorDao.findAll().size());

    }

}
