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

import com.example.demo.dao.ProducreviewDao;
import com.example.demo.model.prod.Productreview;

@RestController
@RequestMapping("/api/prdreviews")
public class ProductreviewRestController implements ProductreviewRestControllerI {

    @Autowired
    ProducreviewDao prdao;

    @PostMapping
    @Override
    public void save(@RequestBody Productreview pr) {
        prdao.save(pr);

    }

    @PutMapping
    @Override
    public void update(@RequestBody Productreview pr) {
        prdao.update(pr);

    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable("id") Integer id) {
        prdao.delete(prdao.findById(id));

    }

    @GetMapping
    @Override
    public Iterable<Productreview> getAll() {
        return prdao.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Productreview getById(@PathVariable("id") Integer id) {
        return prdao.findById(id);
    }

}
