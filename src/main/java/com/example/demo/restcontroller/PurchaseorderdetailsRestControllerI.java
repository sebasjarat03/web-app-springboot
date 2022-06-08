package com.example.demo.restcontroller;

import com.example.demo.model.prchasing.Purchaseorderdetail;

public interface PurchaseorderdetailsRestControllerI {
    void save(Purchaseorderdetail pod);

    void update(Purchaseorderdetail pod);

    void delete(Integer id);

    Iterable<Purchaseorderdetail> findAll();

    Purchaseorderdetail findById(Integer id);
}
