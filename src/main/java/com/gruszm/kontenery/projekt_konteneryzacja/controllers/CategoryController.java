package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.projekt_konteneryzacja.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController
{
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllUsers(Model model)
    {
        model.addAttribute("categories", categoryService.findAll());

        return "categories/show-categories";
    }
}
