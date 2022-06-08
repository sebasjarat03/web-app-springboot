package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.prod.Productreview;

@Repository
@Scope("singleton")
public class ProducreviewDao implements IProductreviewDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Productreview pr) {
        entityManager.persist(pr);

    }

    @Transactional
    @Override
    public void update(Productreview pr) {
        entityManager.merge(pr);

    }

    @Transactional
    @Override
    public void delete(Productreview pr) {
        entityManager.remove(pr);

    }

    @Transactional
    @Override
    public Productreview findById(Integer id) {
        String jpql = "SELECT pr FROM Productreview pr WHERE pr.productreviewid = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        return (Productreview) query.getSingleResult();
    }

    @Transactional
    @Override
    public List<Productreview> findAll() {
        String jpql = "Select pr from Productreview pr";
        return entityManager.createQuery(jpql).getResultList();
    }

}
