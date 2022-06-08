package com.example.demo.restcontroller;

import com.example.demo.model.prod.Productreview;

public interface ProductreviewRestControllerI {
    void save(Productreview pr);

    void update(Productreview pr);

    void delete(Integer id);

    Iterable<Productreview> getAll();

    Productreview getById(Integer id);
}
