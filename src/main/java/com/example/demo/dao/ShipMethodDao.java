package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.example.demo.model.prchasing.Shipmethod;

import org.springframework.stereotype.Repository;

@Repository
public class ShipMethodDao implements IShipMethodDao {

    @PersistenceContext
    public EntityManager entityManager;

    @Transactional
    @Override
    public Shipmethod save(Shipmethod shipMethod) {
        return entityManager.merge(shipMethod);

    }

    @Override
    public void update(Shipmethod shipMethod) {
        entityManager.merge(shipMethod);

    }

    @Override
    public void delete(Shipmethod shipMethod) {
        entityManager.remove(shipMethod);

    }

    @Override
    public List<Shipmethod> findAll() {
        String jpql = "Select s from Shipmethod s";
        return entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public List<Shipmethod> findByName(String name) {
        String jpql = "SELECT sm FROM Shipmethod sm WHERE sm.name = :name";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public Shipmethod findById(Integer id) {
        String jpql = "SELECT sm FROM Shipmethod sm WHERE sm.shipmethodid = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        return (Shipmethod) query.getSingleResult();
    }

}
