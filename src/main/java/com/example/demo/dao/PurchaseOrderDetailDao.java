package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.model.prchasing.Purchaseorderdetail;

import org.springframework.stereotype.Repository;

@Repository
public class PurchaseOrderDetailDao implements IPurchaseOrderDetailDao {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void save(Purchaseorderdetail purchaseOrderDetail) {
        entityManager.persist(purchaseOrderDetail);

    }

    @Override
    public void update(Purchaseorderdetail purchaseOrderDetail) {
        entityManager.merge(purchaseOrderDetail);

    }

    @Override
    public void delete(Purchaseorderdetail purchaseOrderDetail) {
        entityManager.remove(purchaseOrderDetail);

    }

    @Override
    public List<Purchaseorderdetail> findAll() {
        String jpql = "Select pod from Purchaseorderdetail pod";
        return entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public List<Purchaseorderdetail> findByProductId(Integer productId) {
        String jpql = "SELECT pod FROM Purchaseorderdetail pod WHERE pod.productid = :productId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("productId", productId);
        return query.getResultList();
    }

    @Override
    public List<Purchaseorderdetail> findByUnitPrice(Double unitPrice) {
        String jpql = "SELECT pod FROM Purchaseorderdetail pod WHERE pod.unitprice = :unitPrice";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("unitPrice", unitPrice);
        return query.getResultList();
    }

    @Override
    public Purchaseorderdetail findById(Integer id) {
        String jpql = "SELECT pod FROM Purchaseorderdetail pod WHERE pod.id = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        return (Purchaseorderdetail) query.getSingleResult();
    }

}
