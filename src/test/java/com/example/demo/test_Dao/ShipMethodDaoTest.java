package com.example.demo.test_Dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.Taller1Application;
import com.example.demo.dao.IShipMethodDao;
import com.example.demo.model.prchasing.Shipmethod;

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
public class ShipMethodDaoTest {

    @Autowired
    private IShipMethodDao smDao;

    private static Shipmethod sm;

    @Autowired
    public ShipMethodDaoTest(IShipMethodDao smDao) {
        this.smDao = smDao;
    }

    public void createShipMethod() {

        sm = new Shipmethod();
        sm.setName("Ship method test");
        sm.setShipbase(new BigDecimal(45));
        sm.setShiprate(new BigDecimal(4));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(1)
    @Test
    public void saveSMethodTest() {

        createShipMethod();
        sm = smDao.save(sm);
        System.out.println(sm);
        // 2 porque ya hay un vendedor guardado desde el commandlinerunner
        assertEquals(2, smDao.findAll().size());

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(2)
    @Test
    public void updateSMethodTest() {
        Shipmethod sm1 = new Shipmethod();
        sm1.setName("ShipTest");
        sm1.setShipbase(new BigDecimal(35));
        sm1.setShiprate(new BigDecimal(7));
        Shipmethod scopy = smDao.save(sm1);
        scopy.setName("ShipOne");
        smDao.update(scopy);
        assertFalse(smDao.findByName("ShipOne").isEmpty());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(2)
    @Test
    public void findAllTest() {
        List<Shipmethod> lista = smDao.findAll();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getShipmethodid());
        }
        assertEquals(3, lista.size());

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Order(2)
    @Test
    public void deleteTest() {
        System.out.println("IDDELETE: " + sm.getShipmethodid());
        Shipmethod toDel = smDao.findById(2);
        smDao.delete(toDel);
        assertEquals(2, smDao.findAll().size());
    }
}
