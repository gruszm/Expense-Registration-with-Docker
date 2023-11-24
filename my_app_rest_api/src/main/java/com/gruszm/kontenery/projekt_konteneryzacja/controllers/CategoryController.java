package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.entities.*;
import com.gruszm.kontenery.http.AdditionalHttpStatus;
import com.gruszm.kontenery.projekt_konteneryzacja.exceptions.CategoryAlreadyExistsException;
import com.gruszm.kontenery.projekt_konteneryzacja.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Category>> getAllCategories()
    {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable(name = "name") String name)
    {
        Category category = categoryService.findByName(name);

        if (category == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(category);
        }
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category)
    {
        try
        {
            categoryService.save(category);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        }
        catch (CategoryAlreadyExistsException e)
        {
            return ResponseEntity.status(AdditionalHttpStatus.ALREADY_EXISTS).body(null);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable String id)
    {
        Category category = categoryService.findById(id);

        if (category == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            categoryService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        }
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Category> deleteCategoryByName(@PathVariable String name)
    {
        Category category = categoryService.findByName(name);

        if (category == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            categoryService.deleteByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        }
    }
}
