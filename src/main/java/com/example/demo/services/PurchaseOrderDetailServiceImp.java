package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.PurchaseOrderDetailDao;
import com.example.demo.dao.PurchaseOrderHeaderDao;
import com.example.demo.model.prchasing.Purchaseorderdetail;
import com.example.demo.model.prchasing.Purchaseorderheader;

@Service
public class PurchaseOrderDetailServiceImp implements PurchaseOrderDetailService {

    PurchaseOrderDetailDao podr;
    PurchaseOrderHeaderDao pohr;

    @Autowired
    public PurchaseOrderDetailServiceImp(PurchaseOrderDetailDao podr, PurchaseOrderHeaderDao pohr) {
        this.podr = podr;
        this.pohr = pohr;
    }

    @Transactional
    @Override
    public boolean save(Purchaseorderdetail pod, Integer orderHId) {
        boolean created = false;
        Purchaseorderheader header = pohr.findById(orderHId);
        if (pod == null) {
            throw new IllegalArgumentException("purchase order detail must not be null");
        } else if (pod.getOrderqty() < 0) {
            throw new IllegalArgumentException("purchase order quantity must be greater than 0");
        } else if (pod.getUnitprice().compareTo(0.0) < 0) {
            throw new IllegalArgumentException("unit price must be greater than zero");
        } else if (header == null) {
            throw new IllegalArgumentException("purchase order header doesn't exits");
        } else {
            pod.setPurchaseorderheader(header);
            podr.save(pod);
            created = true;
        }

        return created;
    }

    @Transactional
    @Override
    public boolean edit(Purchaseorderdetail pod, Integer orderHId) {
        boolean edited = false;
        Purchaseorderheader header = pohr.findById(orderHId);
        if (pod == null) {
            throw new IllegalArgumentException("purchase order detail must not be null");
        } else {
            Purchaseorderdetail purd = podr.findById(pod.getId());
            if (pod.getOrderqty() < 0) {
                throw new IllegalArgumentException("purchase order quantity must be greater than 0");
            } else if (pod.getUnitprice().compareTo(0.0) < 0) {
                throw new IllegalArgumentException("unit price must be greater than zero");
            } else if (header == null) {
                throw new IllegalArgumentException("purchase order header doesn't exits");
            } else {
                Purchaseorderdetail np = purd;
                np.setDuedate(pod.getDuedate());
                np.setId(pod.getId());
                np.setModifieddate(pod.getModifieddate());
                np.setOrderqty(pod.getOrderqty());
                np.setProductid(pod.getProductid());
                np.setPurchaseorderheader(header);
                np.setReceivedqty(pod.getReceivedqty());
                np.setRejectedqty(pod.getRejectedqty());
                np.setUnitprice(pod.getUnitprice());
                podr.update(np);
                edited = true;
            }
        }
        return edited;
    }

    @Transactional
    public void delete(Purchaseorderdetail pod) {
        podr.delete(pod);
    }

    @Transactional
    public Iterable<Purchaseorderdetail> findAll() {
        return podr.findAll();
    }

    @Transactional
    public Purchaseorderdetail findById(Integer id) {
        return podr.findById(id);
    }

}
