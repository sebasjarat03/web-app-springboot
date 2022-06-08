package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.prchasing.Shipmethod;

public interface IShipMethodDao {
    Shipmethod save(Shipmethod shipMethod);

    void update(Shipmethod shipMethod);

    void delete(Shipmethod shipMethod);

    List<Shipmethod> findAll();

    List<Shipmethod> findByName(String name);

    Shipmethod findById(Integer id);
}
