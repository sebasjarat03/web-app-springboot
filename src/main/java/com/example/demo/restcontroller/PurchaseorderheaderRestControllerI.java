package com.example.demo.restcontroller;

import com.example.demo.model.prchasing.Purchaseorderheader;

public interface PurchaseorderheaderRestControllerI {
    void save(Purchaseorderheader poh);

    void update(Purchaseorderheader poh);

    void delete(Integer id);

    Iterable<Purchaseorderheader> findAll();

    Purchaseorderheader findById(Integer id);
}
