package com.example.demo.restcontroller;

import com.example.demo.model.prod.Billofmaterial;

public interface BillofmaterialRestControllerI {
    void save(Billofmaterial bm);

    void update(Billofmaterial bm);

    void delete(Integer id);

    Iterable<Billofmaterial> getAll();

    Billofmaterial getById(Integer id);
}