package com.gruszm.kontenery.projekt_konteneryzacja.services;

import com.gruszm.kontenery.entities.Expense;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService
{
    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository)
    {
        this.expenseRepository = expenseRepository;
    }

    public void save(Expense expense)
    {
        expenseRepository.save(expense);
    }

    public void deleteById(String id)
    {
        expenseRepository.deleteById(id);
    }

    public List<Expense> findAll()
    {
        return expenseRepository.findAll();
    }

    public Expense findById(String id)
    {
        Optional<Expense> expense = expenseRepository.findById(id);

        return expense.isPresent() ? expense.get() : null;
    }

    public List<Expense> findByCategoryName(String categoryName)
    {
        List<Expense> expenses = expenseRepository.findAll();
        expenses = expenses.stream().filter(expense -> expense.getCategory().getName().equals(categoryName)).toList();
        return expenses;
    }
}
