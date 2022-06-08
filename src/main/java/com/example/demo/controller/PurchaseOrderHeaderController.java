package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.businessdelegate.BusinessDelegate;
import com.example.demo.model.groups.Add;
import com.example.demo.model.prchasing.Purchaseorderheader;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.PurchaseOrderHeaderRepository;
import com.example.demo.services.PurchaseOrderHeaderService;

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
@RequestMapping("/purchase-order-headers")
public class PurchaseOrderHeaderController {
    private BusinessDelegate bd;
    private EmployeeRepository er;

    @Autowired
    public PurchaseOrderHeaderController(BusinessDelegate bd,
            EmployeeRepository er) {
        this.bd = bd;
        this.er = er;
    }

    @GetMapping("")
    public String pohIndex(Model model) {
        model.addAttribute("purchaseorderheaders", bd.findAllPoh());
        return "purchase-order-headers/index";
    }

    @GetMapping("/add")
    public String addTemplatePoh(Model model) {
        model.addAttribute("purchaseorderheader", new Purchaseorderheader());
        model.addAttribute("employees", er.findAll());

        return "purchase-order-headers/add";
    }

    @PostMapping("/add")
    public String savePoh(@Validated(Add.class) @ModelAttribute Purchaseorderheader poh, BindingResult bindingResult,
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("purchaseorderheader", poh);
                model.addAttribute("employees", er.findAll());

                return "purchase-order-headers/add";
            }

            bd.savePoh(poh);
        }
        return "redirect:/purchase-order-headers/";
    }

    @GetMapping("/edit/{id}")
    public String editTemplatePoh(Model model, @PathVariable("id") Integer id) {
        Purchaseorderheader poh = bd.findByIdPoh(id);
        if (poh == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        model.addAttribute("purchaseorderheader", poh);
        model.addAttribute("employees", er.findAll());

        return "/purchase-order-headers/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPoh(@Validated(Add.class) @ModelAttribute Purchaseorderheader poh, BindingResult bindingResult,
            Model model, @PathVariable("id") Integer id,
            @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("purchaseorderheader", poh);
                model.addAttribute("employees", er.findAll());

                return "/purchase-order-headers/edit";
            }

            bd.editPoh(poh);
        }
        return "redirect:/purchase-order-headers/";
    }

    @GetMapping("/del/{id}")
    public String deletePoh(@PathVariable Integer id, Model model) {
        Purchaseorderheader poh = bd.findByIdPoh(id);
        if (poh == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        bd.deletePoh(poh);
        model.addAttribute("purchaseorderheaders", bd.findAllPoh());
        return "redirect:/purchase-order-headers/";
    }

    @GetMapping("/{id}")
    public String getPoh(@PathVariable Integer id, Model model) {
        Purchaseorderheader poh = bd.findByIdPoh(id);
        if (poh == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        model.addAttribute("purchaseorderheader", poh);

        return "purchase-order-headers/info";
    }

}
