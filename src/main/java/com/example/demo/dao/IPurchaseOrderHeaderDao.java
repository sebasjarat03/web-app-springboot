package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.prchasing.Purchaseorderheader;

public interface IPurchaseOrderHeaderDao {
    void save(Purchaseorderheader purchaseOrderHeader);

    void update(Purchaseorderheader purchaseOrderHeader);

    void delete(Purchaseorderheader purchaseOrderHeader);

    List<Purchaseorderheader> findAll();

    Purchaseorderheader findById(Integer id);

    List<Purchaseorderheader> findByShipMethodId(Integer shipmentMethodId);

    List<Purchaseorderheader> findByVendorId(Integer vendorId);

}
