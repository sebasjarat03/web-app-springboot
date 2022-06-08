package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.businessdelegate.BusinessDelegate;

@Controller
@RequestMapping("/custom-queries")
public class CustomQueriesController {
    private BusinessDelegate bd;

    @Autowired
    public CustomQueriesController(BusinessDelegate bd) {
        this.bd = bd;
    }

    @GetMapping("")
    public String index() {
        return "custom-queries/index";
    }

    @GetMapping("/atleast2")
    public String findByAtLeast2(Model model) {
        model.addAttribute("purchaseorderheaders", bd.findAtleast2());

        return "custom-queries/pohs2";
    }

}