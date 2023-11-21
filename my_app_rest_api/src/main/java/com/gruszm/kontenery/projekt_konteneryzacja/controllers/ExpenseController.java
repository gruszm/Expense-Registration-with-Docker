package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.entities.Expense;
import com.gruszm.kontenery.projekt_konteneryzacja.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
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

    @GetMapping("/name/{name}")
    public List<Expense> getAllByCategoryName(@PathVariable(name = "name") String name)
    {
        return expenseService.findByCategoryName(name);
    }

    @GetMapping("/email/{email}")
    public List<Expense> getAllByEmail(@PathVariable(name = "email") String email)
    {
        return expenseService.findByUserEmail(email);
    }

    @PostMapping
    public void saveExpense(@RequestBody Expense expense)
    {
        expenseService.save(expense);
    }

    @DeleteMapping("/id/{id}")
    public void deleteExpenseById(@PathVariable(name = "id") String id)
    {
        expenseService.deleteById(id);
    }
}
