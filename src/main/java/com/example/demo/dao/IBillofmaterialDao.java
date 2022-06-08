package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.prod.Billofmaterial;

public interface IBillofmaterialDao {
    void save(Billofmaterial bill);

    void update(Billofmaterial bill);

    void delete(Billofmaterial bill);

    Billofmaterial findById(Integer id);

    List<Billofmaterial> findAll();
}
