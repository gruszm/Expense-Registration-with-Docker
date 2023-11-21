package com.gruszm.kontenery.controllers;

import com.gruszm.kontenery.entities.Expense;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController
{
    @Value("${my.app.rest.api.host}")
    private String host;

    @Value("${my.app.rest.api.port}")
    private String port;

    @GetMapping
    public String getAllExpenses(Model model)
    {
        String uri = "http://" + host + ":" + port + "/api/expenses";
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        List<Expense> expenses = restTemplate.getForObject(uri, List.class);
        model.addAttribute("expenses", expenses);
        return "expenses/show-expenses";
    }
}
