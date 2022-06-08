package com.example.demo.restcontroller;

import java.util.List;

import com.example.demo.model.prchasing.Purchaseorderheader;

public interface CustomQueriesRestControllerI {
    List<Purchaseorderheader> findAtLeastTwo();
}
