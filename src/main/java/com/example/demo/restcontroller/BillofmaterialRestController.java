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

import com.example.demo.dao.BillofmaterialDao;
import com.example.demo.model.prod.Billofmaterial;

@RestController
@RequestMapping("/api/bills")
public class BillofmaterialRestController implements BillofmaterialRestControllerI {

    @Autowired
    BillofmaterialDao bmdao;

    @PostMapping
    @Override
    public void save(@RequestBody Billofmaterial bm) {
        bmdao.save(bm);

    }

    @PutMapping
    @Override
    public void update(@RequestBody Billofmaterial bm) {
        bmdao.update(bm);

    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable("id") Integer id) {
        bmdao.delete(bmdao.findById(id));

    }

    @GetMapping
    @Override
    public Iterable<Billofmaterial> getAll() {
        return bmdao.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Billofmaterial getById(@PathVariable("id") Integer id) {
        return bmdao.findById(id);
    }

}