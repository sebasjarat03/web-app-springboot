package com.example.demo.restcontroller;

import com.example.demo.model.prchasing.Shipmethod;

public interface ShipmethodRestControllerI {
    void save(Shipmethod sm);

    void update(Shipmethod sm);

    void delete(Integer id);

    Iterable<Shipmethod> getAll();

    Shipmethod getById(Integer id);
}
