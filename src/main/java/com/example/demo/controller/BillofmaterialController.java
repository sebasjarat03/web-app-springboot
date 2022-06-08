package com.example.demo.controller;

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

import com.example.demo.businessdelegate.BusinessDelegate;
import com.example.demo.model.groups.Add;
import com.example.demo.model.prod.Billofmaterial;

@Controller
@RequestMapping("/bill-of-materials")
public class BillofmaterialController {
    private BusinessDelegate bd;

    @Autowired
    public BillofmaterialController(BusinessDelegate bd) {
        this.bd = bd;
    }

    @GetMapping("")
    public String bmIndex(Model model) {
        model.addAttribute("billofmaterials", bd.findAllBillofmaterial());
        return "/bill-of-materials/index";
    }

    @GetMapping("/add")
    public String addTemplateBm(Model model) {
        model.addAttribute("billofmaterial", new Billofmaterial());

        return "bill-of-materials/add";
    }

    @PostMapping("/add")
    public String saveBm(@Validated(Add.class) @ModelAttribute Billofmaterial bm, BindingResult bindingResult,
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("billofmaterial", bm);
                // model.addAttribute("businessentities", ber.findAll());

                return "bill-of-materials/add";
            }

            bd.saveBill(bm);

        }
        return "redirect:/bill-of-materials/";
    }

    @GetMapping("/edit/{id}")
    public String editTemplateBm(Model model, @PathVariable("id") Integer id) {
        Billofmaterial bm = bd.findByIdBillofmaterial(id);
        if (bm == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        model.addAttribute("billofmaterial", bm);

        return "/bill-of-materials/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBm(@Validated(Add.class) @ModelAttribute Billofmaterial bm, BindingResult bindingResult,
            Model model, @PathVariable("id") Integer id,
            @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("billofmaterial", bm);

                return "bill-of-materials/edit";
            }

            bd.editBill(bm);
        }
        return "redirect:/bill-of-materials/";
    }

    @GetMapping("/del/{id}")
    public String deletePr(@PathVariable Integer id, Model model) {
        Billofmaterial bm = bd.findByIdBillofmaterial(id);
        if (bm == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        bd.deleteBill(bm);
        model.addAttribute("billofmaterial", bd.findAllBillofmaterial());
        return "redirect:/bill-of-materials/";
    }

    @GetMapping("/{id}")
    public String getPr(@PathVariable Integer id, Model model) {
        Billofmaterial bm = bd.findByIdBillofmaterial(id);
        if (bm == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        model.addAttribute("billofmaterial", bm);

        return "bill-of-materials/info";
    }

}