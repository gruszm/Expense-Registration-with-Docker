package com.gruszm.kontenery.controllers;

import com.gruszm.kontenery.entities.Category;
import com.gruszm.kontenery.http.AdditionalHttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController
{
    @Value("${my.app.rest.api.host}")
    private String host;

    @Value("${my.app.rest.api.port}")
    private String port;

    @GetMapping
    public String getAllCategories(Model model)
    {
        String url = "http://" + host + ":" + port + "/api/categories";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Category[]> categoriesResponse = restTemplate.getForEntity(url, Category[].class);
        List<Category> categories = Arrays.asList(categoriesResponse.getBody());
        model.addAttribute("categories", categories);
        return "categories/show-categories";
    }

    @GetMapping("/categoryForm")
    public String getCategoryForm(Model model)
    {
        Category category = new Category();
        model.addAttribute("category", category);
        return "categories/category-form";
    }

    @PostMapping("/processCategoryForm")
    public String processCategoryForm(@ModelAttribute(name = "category") Category category, Errors errors, RedirectAttributes redirectAttributes)
    {
        // trim the id to null, if it's an empty string
        category.setId(category.getId().equals("") ? null : category.getId());

        String url = "http://" + host + ":" + port + "/api/categories";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Category> requestEntity = new HttpEntity<>(category, headers);
        ResponseEntity<Category> categoryResponse = restTemplate.postForEntity(url, requestEntity, Category.class);

        if (categoryResponse.getStatusCode().value() == AdditionalHttpStatus.ALREADY_EXISTS)
        {
            errors.rejectValue("name", "category.already.exists", "Category with name \"" + category.getName() + "\" already exists in the database");
            return "categories/category-form";
        }
        else
        {
            redirectAttributes.addAttribute("categoryAdded", "");
            return "redirect:/categories";
        }
    }

    @PostMapping("/modifyCategory")
    public String modifyCategory(@RequestParam(name = "categoryNameToModify") String name, Model model)
    {
        String url = "http://" + host + ":" + port + "/api/categories" + "/name/" + name;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Category> categoryResponse = restTemplate.getForEntity(url, Category.class);

        model.addAttribute("category", categoryResponse.getBody());

        return "categories/category-form";
    }

    @PostMapping("/deleteCategory")
    public String deleteMapping(@RequestParam(name = "categoryIdToDelete") String id, RedirectAttributes redirectAttributes)
    {
        String url = "http://" + host + ":" + port + "/api/categories" + "/id/" + id;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Category> categoryResponse = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Category.class);

        redirectAttributes.addAttribute("categoryDeleted", "");

        return "redirect:/categories";
    }
}
