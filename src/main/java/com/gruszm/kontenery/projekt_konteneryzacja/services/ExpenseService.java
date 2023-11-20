package com.gruszm.kontenery.projekt_konteneryzacja.services;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.Expense;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseService
{
    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository)
    {
        this.expenseRepository = expenseRepository;
    }

    @Transactional
    public void saveAll(Expense... expenses)
    {
        for (Expense e : expenses)
        {
            expenseRepository.save(e);
        }
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

    public List<Expense> findByCategoryName(String categoryName)
    {
        List<Expense> expenses = expenseRepository.findAll();
        expenses = expenses.stream().filter(expense -> expense.getCategory().getName().equals(categoryName)).toList();
        return expenses;
    }

    public List<Expense> findByUserEmail(String userEmail)
    {
        List<Expense> expenses = expenseRepository.findAll();
        expenses = expenses.stream().filter(expense -> expense.getUser().getEmail().equals(userEmail)).toList();
        return expenses;
    }
}
