package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CustomQueriesDao;
import com.example.demo.model.prchasing.Purchaseorderheader;

@RestController
@RequestMapping("/api/cusqueries")
public class CustomQueriesRestController implements CustomQueriesRestControllerI {

    @Autowired
    private CustomQueriesDao cqDao;

    @GetMapping("/at-least2")
    @Override
    public List<Purchaseorderheader> findAtLeastTwo() {
        return cqDao.findByLeast2();
    }

}