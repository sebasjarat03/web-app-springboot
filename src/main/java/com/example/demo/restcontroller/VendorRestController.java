package com.example.demo.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.prchasing.Vendor;
import com.example.demo.services.VendorServiceImp;

@RestController
@RequestMapping("/api/vendrs")
public class VendorRestController implements VendorRestControllerI {

    @Autowired
    private VendorServiceImp vService;

    @PostMapping
    @Override
    public void saveVendor(@RequestBody Vendor v) {
        vService.save(v);

    }

    @PutMapping
    @Override
    public void updateVendor(@RequestBody Vendor v) {
        vService.edit(v, v.getBusinessentityid());

    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteVendor(@PathVariable("id") Integer id) {
        vService.delete(vService.findById(id));

    }

    @GetMapping
    @Override
    public Iterable<Vendor> getVendors() {
        return vService.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Vendor getVendorById(@PathVariable("id") Integer id) {
        return vService.findById(id);
    }

}
