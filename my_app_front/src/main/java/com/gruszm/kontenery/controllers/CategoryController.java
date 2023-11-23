package com.gruszm.kontenery.controllers;

import com.gruszm.kontenery.entities.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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
}
