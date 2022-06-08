package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.model.prchasing.Purchaseorderheader;

import org.springframework.stereotype.Repository;

@Repository
public class PurchaseOrderHeaderDao implements IPurchaseOrderHeaderDao {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void save(Purchaseorderheader purchaseOrderHeader) {
        entityManager.persist(purchaseOrderHeader);

    }

    @Override
    public void update(Purchaseorderheader purchaseOrderHeader) {
        entityManager.merge(purchaseOrderHeader);

    }

    @Override
    public void delete(Purchaseorderheader purchaseOrderHeader) {
        entityManager.remove(purchaseOrderHeader);

    }

    @Override
    public List<Purchaseorderheader> findAll() {
        String jpql = "Select poh from Purchaseorderheader poh";
        return entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public List<Purchaseorderheader> findByShipMethodId(Integer shipmentMethodId) {
        String jpql = "SELECT poh FROM Purchaseorderheader poh WHERE poh.shipmethod.shipmethodid = :shipMethodId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("shipMethodId", shipmentMethodId);
        return query.getResultList();
    }

    @Override
    public List<Purchaseorderheader> findByVendorId(Integer vendorId) {
        String jpql = "SELECT poh FROM Purchaseorderheader poh WHERE poh.vendor.businessentityid = :vendorId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("vendorId", vendorId);
        return query.getResultList();
    }

    @Override
    public Purchaseorderheader findById(Integer id) {
        String jpql = "SELECT poh FROM Purchaseorderheader poh WHERE poh.purchaseorderid = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        return (Purchaseorderheader) query.getSingleResult();
    }

}
