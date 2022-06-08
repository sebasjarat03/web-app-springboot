package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.prchasing.Vendor;

public interface IVendorDao {
    void save(Vendor vendor);

    void update(Vendor vendor);

    void delete(Vendor vendor);

    List<Vendor> findAll();

    List<Vendor> findByCreditRate(Integer creditRate);

    List<Vendor> findByName(String name);

    List<Vendor> findByPreferredStat(String preferredStat);

    Vendor findById(Integer id);

}
