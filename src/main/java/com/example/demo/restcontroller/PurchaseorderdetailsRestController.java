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

import com.example.demo.model.prchasing.Purchaseorderdetail;
import com.example.demo.services.PurchaseOrderDetailServiceImp;

@RestController
@RequestMapping("/api/pods")
public class PurchaseorderdetailsRestController implements PurchaseorderdetailsRestControllerI {

    @Autowired
    public PurchaseOrderDetailServiceImp service;

    @PostMapping
    @Override
    public void save(@RequestBody Purchaseorderdetail pod) {
        service.save(pod, pod.getPurchaseorderheader().getPurchaseorderid());

    }

    @PutMapping
    @Override
    public void update(@RequestBody Purchaseorderdetail pod) {
        service.edit(pod, pod.getPurchaseorderheader().getPurchaseorderid());

    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable("id") Integer id) {
        service.delete(service.findById(id));

    }

    @GetMapping
    @Override
    public Iterable<Purchaseorderdetail> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Purchaseorderdetail findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

}
