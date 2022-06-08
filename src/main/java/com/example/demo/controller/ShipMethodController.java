package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.businessdelegate.BusinessDelegate;
import com.example.demo.model.groups.Add;
import com.example.demo.model.prchasing.Shipmethod;
import com.example.demo.repositories.ShipMethodRepository;
import com.example.demo.services.ShipMethodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ship-methods")
public class ShipMethodController {
    private BusinessDelegate bd;

    @Autowired
    public ShipMethodController(BusinessDelegate bd) {
        this.bd = bd;
    }

    @GetMapping("")
    public String shipmethodIndex(Model model) {
        model.addAttribute("shipmethods", bd.findAllShipMethods());
        return "ship-methods/index";
    }

    @GetMapping("/add")
    public String addTemplateShipmethod(Model model) {
        model.addAttribute("shipmethod", new Shipmethod());

        return "/ship-methods/add";
    }

    @PostMapping("/add")
    public String save(@Validated(Add.class) @ModelAttribute Shipmethod shipmethod, BindingResult bindingResult,
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("shipmethod", shipmethod);
                // model.addAttribute("businessentities", ber.findAll());

                return "ship-methods/add";
            }

            bd.saveShipMethod(shipmethod);
        }
        return "redirect:/ship-methods/";
    }

    @GetMapping("/edit/{id}")
    public String editTemplateShipmethod(Model model, @PathVariable("id") Integer id) {
        Shipmethod shipmethod = bd.findByIdShipMethod(id);
        if (shipmethod == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        model.addAttribute("shipmethod", shipmethod);

        return "/ship-methods/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Validated(Add.class) @ModelAttribute Shipmethod shipmethod, BindingResult bindingResult,
            Model model, @PathVariable("id") Integer id,
            @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("shipmethod", shipmethod);

                return "ship-methods/edit";
            }

            bd.editShipMethod(shipmethod);
        }
        return "redirect:/ship-methods/";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        Shipmethod shipmethod = bd.findByIdShipMethod(id);
        if (shipmethod == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        bd.deleteShipMethod(shipmethod);
        model.addAttribute("shipmethods", bd.findAllShipMethods());
        return "redirect:/ship-methods/";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id, Model model) {
        Shipmethod shipmethod = bd.findByIdShipMethod(id);
        if (shipmethod == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        model.addAttribute("shipmethod", shipmethod);

        return "ship-methods/info";
    }

}
