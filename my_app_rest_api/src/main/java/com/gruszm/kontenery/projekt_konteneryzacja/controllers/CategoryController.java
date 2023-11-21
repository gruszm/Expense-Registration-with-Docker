package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.entities.*;
import com.gruszm.kontenery.projekt_konteneryzacja.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/categories")
public class CategoryController
{
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories()
    {
        return categoryService.findAll();
    }

    @GetMapping("/name/{name}")
    public Category getCategoryByName(@PathVariable(name = "name") String name)
    {
        return categoryService.findByName(name);
    }

    @PostMapping
    public void saveCategory(@RequestBody Category category)
    {
        categoryService.save(category);
    }

    @DeleteMapping("/id/{id}")
    public void deleteCategoryById(@PathVariable String id)
    {
        categoryService.deleteById(id);
    }

    @DeleteMapping("/name/{name}")
    public void deleteCategoryByName(@PathVariable String name)
    {
        categoryService.deleteByName(name);
    }
}
