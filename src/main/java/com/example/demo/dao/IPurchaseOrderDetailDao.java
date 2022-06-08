package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.prchasing.Purchaseorderdetail;

public interface IPurchaseOrderDetailDao {
    void save(Purchaseorderdetail purchaseOrderDetail);

    void update(Purchaseorderdetail purchaseOrderDetail);

    void delete(Purchaseorderdetail purchaseOrderDetail);

    List<Purchaseorderdetail> findAll();

    List<Purchaseorderdetail> findByProductId(Integer productId);

    List<Purchaseorderdetail> findByUnitPrice(Double unitPrice);

    Purchaseorderdetail findById(Integer id);
}
