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

import com.example.demo.model.prchasing.Purchaseorderheader;
import com.example.demo.services.PurchaseOrderHeaderServiceImp;

@RestController
@RequestMapping("/api/pohs")
public class PurchaseorderheaderRestController implements PurchaseorderheaderRestControllerI {

    @Autowired
    public PurchaseOrderHeaderServiceImp service;

    @PostMapping
    @Override
    public void save(@RequestBody Purchaseorderheader poh) {
        service.save(poh, 0, poh.getEmployeeid());

    }

    @PutMapping
    @Override
    public void update(@RequestBody Purchaseorderheader poh) {
        service.edit(poh, 0, poh.getEmployeeid());

    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable("id") Integer id) {
        service.delete(service.findById(id));

    }

    @GetMapping
    @Override
    public Iterable<Purchaseorderheader> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Purchaseorderheader findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

}
