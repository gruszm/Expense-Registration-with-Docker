package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.entities.Category;
import com.gruszm.kontenery.entities.Expense;
import com.gruszm.kontenery.projekt_konteneryzacja.services.CategoryService;
import com.gruszm.kontenery.projekt_konteneryzacja.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController
{
    private ExpenseService expenseService;
    private CategoryService categoryService;

    @Autowired
    public ExpenseController(ExpenseService expenseService, CategoryService categoryService)
    {
        this.expenseService = expenseService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses()
    {
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable(name = "id") String id)
    {
        Expense expense = expenseService.findById(id);

        if (expense == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(expense);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Expense>> getAllByCategoryName(@PathVariable(name = "name") String name)
    {
        Category category = categoryService.findByName(name);

        if (category == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(expenseService.findByCategoryName(name));
        }
    }

    @PostMapping
    public ResponseEntity<Expense> saveExpense(@RequestBody Expense expense)
    {
        expenseService.save(expense);
        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Expense> deleteExpenseById(@PathVariable(name = "id") String id)
    {
        Expense expense = expenseService.findById(id);

        if (expense == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            expenseService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(expense);
        }
    }
}
