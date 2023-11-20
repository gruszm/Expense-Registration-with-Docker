package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.Expense;
import com.gruszm.kontenery.projekt_konteneryzacja.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public List<Expense> getAllExpenses()
    {
        return expenseService.findAll();
    }

    @GetMapping("/category/{categoryName}")
    public List<Expense> getAllByCategoryName(@PathVariable(name = "categoryName") String categoryName)
    {
        return expenseService.findByCategoryName(categoryName);
    }

    @GetMapping("/email/{email}")
    public List<Expense> getAllByEmail(@PathVariable(name = "email") String email)
    {
        return expenseService.findByUserEmail(email);
    }

    @PostMapping
    public void save(@RequestBody Expense expense)
    {
        expenseService.save(expense);
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable(name = "id") String id)
    {
        expenseService.deleteById(id);
    }
}
