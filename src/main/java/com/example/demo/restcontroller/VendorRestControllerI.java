package com.example.demo.restcontroller;

import com.example.demo.model.prchasing.Vendor;

public interface VendorRestControllerI {
    void saveVendor(Vendor v);

    void updateVendor(Vendor v);

    void deleteVendor(Integer id);

    Iterable<Vendor> getVendors();

    Vendor getVendorById(Integer id);
}
