package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.businessdelegate.BusinessDelegate;
import com.example.demo.model.groups.Add;
import com.example.demo.model.prchasing.Vendor;
import com.example.demo.repositories.BusinessEntityRepository;
import com.example.demo.repositories.PurchaseOrderHeaderRepository;
import com.example.demo.repositories.VendorRepository;
import com.example.demo.services.VendorService;

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
@RequestMapping("/vendors")
public class VendorController {
    private BusinessDelegate bd;

    @Autowired
    public VendorController(BusinessDelegate bd) {
        this.bd = bd;
    }

    @GetMapping("")
    public String vendorsIndex(Model model) {
        model.addAttribute("vendors", bd.findAllVendors());
        return "/vendors/index";
    }

    @GetMapping("/add")
    public String addTemplateVendor(Model model) {
        model.addAttribute("vendor", new Vendor());
        // model.addAttribute("businessentities", ber.findAll());

        return "vendors/add";
    }

    @PostMapping("/add")
    public String saveVendor(@Validated(Add.class) @ModelAttribute Vendor vendor, BindingResult bindingResult,
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("vendor", vendor);
                // model.addAttribute("businessentities", ber.findAll());

                return "vendors/add";
            }

            bd.saveVendor(vendor);
            ;
        }
        return "redirect:/vendors/";
    }

    @GetMapping("/edit/{id}")
    public String editTemplateVendor(Model model, @PathVariable("id") Integer id) {
        Vendor vendor = bd.findByIdVendor(id);
        if (vendor == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        model.addAttribute("vendor", vendor);

        return "/vendors/edit";
    }

    @PostMapping("/edit/{id}")
    public String editVendor(@Validated(Add.class) @ModelAttribute Vendor vendor, BindingResult bindingResult,
            Model model, @PathVariable("id") Integer id,
            @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("vendor", vendor);
                // model.addAttribute("businessentities", ber.findAll());

                return "vendors/edit";
            }

            bd.editVendor(vendor);
        }
        return "redirect:/vendors/";
    }

    @GetMapping("/del/{id}")
    public String deleteVendor(@PathVariable Integer id, Model model) {
        Vendor vendor = bd.findByIdVendor(id);
        if (vendor == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        bd.deleteVendor(vendor);
        model.addAttribute("vendors", bd.findAllVendors());
        return "redirect:/vendors/";
    }

    @GetMapping("/{id}")
    public String getVendor(@PathVariable Integer id, Model model) {
        Vendor vendor = bd.findByIdVendor(id);
        if (vendor == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        model.addAttribute("vendor", vendor);

        return "vendors/info";
    }

}
