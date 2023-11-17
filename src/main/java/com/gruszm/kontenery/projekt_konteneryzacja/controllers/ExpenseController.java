package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.projekt_konteneryzacja.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expenses")
public class ExpenseController
{
    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService)
    {
        this.expenseService = expenseService;
    }

    @GetMapping
    public String getAllExpenses(Model model)
    {
        model.addAttribute("expenses", expenseService.findAll());

        return "expenses/show-expenses";
    }
}
