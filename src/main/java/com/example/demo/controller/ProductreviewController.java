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
import com.example.demo.model.prod.Productreview;

@Controller
@RequestMapping("/product-reviews")
public class ProductreviewController {
    private BusinessDelegate bd;

    @Autowired
    public ProductreviewController(BusinessDelegate bd) {
        this.bd = bd;
    }

    @GetMapping("")
    public String prIndex(Model model) {
        model.addAttribute("productreviews", bd.findAllProductreview());
        return "/product-reviews/index";
    }

    @GetMapping("/add")
    public String addTemplatePr(Model model) {
        model.addAttribute("productreview", new Productreview());

        return "product-reviews/add";
    }

    @PostMapping("/add")
    public String savePr(@Validated(Add.class) @ModelAttribute Productreview productreview, BindingResult bindingResult,
            Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("productreview", productreview);
                // model.addAttribute("businessentities", ber.findAll());

                return "product-reviews/add";
            }

            bd.saveProductreview(productreview);

        }
        return "redirect:/product-reviews/";
    }

    @GetMapping("/edit/{id}")
    public String editTemplatePr(Model model, @PathVariable("id") Integer id) {
        Productreview pr = bd.findByIdProductreview(id);
        if (pr == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        model.addAttribute("productreview", pr);

        return "/product-reviews/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPr(@Validated(Add.class) @ModelAttribute Productreview pr, BindingResult bindingResult,
            Model model, @PathVariable("id") Integer id,
            @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("productreview", pr);

                return "product-reviews/edit";
            }

            bd.editProductreview(pr);
        }
        return "redirect:/product-reviews/";
    }

    @GetMapping("/del/{id}")
    public String deletePr(@PathVariable Integer id, Model model) {
        Productreview pr = bd.findByIdProductreview(id);
        if (pr == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        bd.deleteProductreview(pr);
        model.addAttribute("productreviews", bd.findAllProductreview());
        return "redirect:/product-reviews/";
    }

    @GetMapping("/{id}")
    public String getPr(@PathVariable Integer id, Model model) {
        Productreview pr = bd.findByIdProductreview(id);
        if (pr == null) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        model.addAttribute("productreview", pr);

        return "product-reviews/info";
    }
}