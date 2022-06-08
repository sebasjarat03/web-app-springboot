package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.model.prchasing.Vendor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class VendorDao implements IVendorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Vendor vendor) {
        entityManager.persist(vendor);
    }

    @Override
    public void update(Vendor vendor) {
        entityManager.merge(vendor);
    }

    @Override
    public void delete(Vendor vendor) {
        entityManager.remove(vendor);
    }

    @Override
    public List<Vendor> findAll() {
        String jpql = "Select v from Vendor v";
        return entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public List<Vendor> findByCreditRate(Integer creditRate) {
        String jpql = "SELECT v FROM Vendor v WHERE v.creditrating = :creditRate";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("creditRate", creditRate);
        return query.getResultList();
    }

    @Override
    public List<Vendor> findByName(String name) {
        String jpql = "SELECT v FROM Vendor v WHERE v.name = :name";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Vendor> findByPreferredStat(String preferredStat) {
        String jpql = "SELECT v FROM Vendor v WHERE v.preferredvendorstatus = :preferredStat";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("preferredStat", preferredStat);
        return query.getResultList();
    }

    @Override
    public Vendor findById(Integer id) {
        String jpql = "SELECT v FROM Vendor v WHERE v.businessentityid = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        return (Vendor) query.getSingleResult();
    }
}
