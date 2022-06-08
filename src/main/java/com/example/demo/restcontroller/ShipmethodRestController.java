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

import com.example.demo.model.prchasing.Shipmethod;
import com.example.demo.services.ShipMethodServiceImp;

@RestController
@RequestMapping("/api/shmeth")
public class ShipmethodRestController implements ShipmethodRestControllerI {

    @Autowired
    private ShipMethodServiceImp service;

    @PostMapping
    @Override
    public void save(@RequestBody Shipmethod sm) {
        service.save(sm);

    }

    @PutMapping
    @Override
    public void update(@RequestBody Shipmethod sm) {
        service.edit(sm);

    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable("id") Integer id) {
        service.delete(service.findById(id));

    }

    @GetMapping
    @Override
    public Iterable<Shipmethod> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Shipmethod getById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

}
