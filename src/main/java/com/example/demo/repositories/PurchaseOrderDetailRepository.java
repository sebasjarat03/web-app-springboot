package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.prchasing.Purchaseorderdetail;

@Repository
public interface PurchaseOrderDetailRepository extends CrudRepository<Purchaseorderdetail, Integer>{

}
