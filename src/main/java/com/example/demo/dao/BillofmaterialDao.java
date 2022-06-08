package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.prod.Billofmaterial;

@Repository
@Scope("singleton")
public class BillofmaterialDao implements IBillofmaterialDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Billofmaterial bill) {
        entityManager.persist(bill);

    }

    @Transactional
    @Override
    public void update(Billofmaterial bill) {
        entityManager.merge(bill);

    }

    @Transactional
    @Override
    public void delete(Billofmaterial bill) {
        entityManager.remove(bill);

    }

    @Transactional
    @Override
    public Billofmaterial findById(Integer id) {
        String jpql = "SELECT bm FROM Billofmaterial bm WHERE bm.billofmaterialsid = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        return (Billofmaterial) query.getSingleResult();
    }

    @Transactional
    @Override
    public List<Billofmaterial> findAll() {
        String jpql = "Select bm from Billofmaterial bm";
        return entityManager.createQuery(jpql).getResultList();
    }

}
