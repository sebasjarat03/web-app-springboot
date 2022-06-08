package com.example.demo.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ShipMethodDao;
import com.example.demo.model.prchasing.Shipmethod;

@Service
public class ShipMethodServiceImp implements ShipMethodService {

    ShipMethodDao smr;

    @Autowired
    public ShipMethodServiceImp(ShipMethodDao smr) {
        this.smr = smr;
    }

    @Transactional
    @Override
    public boolean save(Shipmethod sm) {
        boolean saved = false;
        if (sm == null) {
            throw new IllegalArgumentException("The ship method must not be null");
        } else if (sm.getShipbase().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The ship base must be greater than zero");
        } else if (sm.getShiprate().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The ship rate must be greater than zero");
        } else if (sm.getName().length() < 4) {
            throw new IllegalArgumentException("The name must be at least 4 characters long");
        } else {
            smr.save(sm);
            saved = true;
        }
        return saved;
    }

    @Transactional
    @Override
    public boolean edit(Shipmethod sm) {
        boolean edited = false;
        if (sm == null) {
            throw new IllegalArgumentException("The ship method must not be null");
        } else {
            Shipmethod shipm = smr.findById(sm.getShipmethodid());
            if (sm.getShipbase().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("The ship base must be greater than zero");
            } else if (sm.getShiprate().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("The ship rate must be greater than zero");
            } else if (sm.getName().length() < 4) {
                throw new IllegalArgumentException("The name must be at least 4 characters long");
            } else if (shipm == null) {
                throw new IllegalArgumentException("The ship methos doesn't exists");
            } else {
                Shipmethod s = shipm;
                s.setModifieddate(sm.getModifieddate());
                s.setName(sm.getName());
                s.setPurchaseorderheaders(sm.getPurchaseorderheaders());
                s.setRowguid(sm.getRowguid());
                s.setShipbase(sm.getShipbase());
                s.setShipmethodid(sm.getShipmethodid());
                s.setShiprate(sm.getShiprate());
                smr.update(s);
                edited = true;
            }
        }
        return edited;
    }

    @Transactional
    public void delete(Shipmethod sm) {
        smr.delete(sm);
    }

    @Transactional
    public Iterable<Shipmethod> findAll() {
        return smr.findAll();
    }

    @Transactional
    public Shipmethod findById(Integer id) {
        return smr.findById(id);
    }

}
