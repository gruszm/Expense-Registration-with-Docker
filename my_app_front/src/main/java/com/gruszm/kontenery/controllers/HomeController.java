package com.gruszm.kontenery.controllers;

import com.gruszm.kontenery.externalapi.CatFact;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController
{
    @GetMapping
    public String getHome(Model model)
    {
        String url = "https://catfact.ninja/fact";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CatFact> catFactResponse = restTemplate.getForEntity(url, CatFact.class);
        model.addAttribute("catfact", catFactResponse.getBody());

        return "home";
    }
}
