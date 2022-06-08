package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.prod.Productreview;

public interface IProductreviewDao {
    void save(Productreview pr);

    void update(Productreview pr);

    void delete(Productreview pr);

    Productreview findById(Integer id);

    List<Productreview> findAll();
}
