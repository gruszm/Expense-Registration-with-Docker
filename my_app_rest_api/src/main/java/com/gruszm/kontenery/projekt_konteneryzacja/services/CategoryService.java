package com.gruszm.kontenery.projekt_konteneryzacja.services;

import com.gruszm.kontenery.entities.Category;
import com.gruszm.kontenery.entities.Expense;
import com.gruszm.kontenery.projekt_konteneryzacja.exceptions.CategoryAlreadyExistsException;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.CategoryRepository;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{
    private CategoryRepository categoryRepository;
    private ExpenseRepository expenseRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ExpenseRepository expenseRepository)
    {
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    @Transactional
    public void saveAll(Category... categories) throws CategoryAlreadyExistsException
    {
        for (Category c : categories)
        {
            if (categoryRepository.findByName(c.getName()) != null)
            {
                throw new CategoryAlreadyExistsException("Category already exists in the database: " + c.getName());
            }

            categoryRepository.save(c);
        }
    }

    public void deleteById(String id)
    {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent())
        {
            List<Expense> expenses = expenseRepository.findAllByCategory(category.get());

            for (Expense e : expenses)
            {
                e.setCategory(null);
            }

            categoryRepository.deleteById(id);
        }
    }

    public void deleteByName(String name)
    {
        Category category = categoryRepository.findByName(name);

        List<Expense> expenses = expenseRepository.findAllByCategory(category);

        for (Expense e : expenses)
        {
            e.setCategory(null);
        }

        categoryRepository.delete(category);
    }

    public void save(Category category) throws CategoryAlreadyExistsException
    {
        if (categoryRepository.findByName(category.getName()) != null)
        {
            throw new CategoryAlreadyExistsException("Category already exists in the database: " + category.getName());
        }

        categoryRepository.save(category);
    }

    public Category findById(String id)
    {
        Optional<Category> category = categoryRepository.findById(id);

        return category.isPresent() ? category.get() : null;
    }

    public Category findByName(String name)
    {
        return categoryRepository.findByName(name);
    }

    public List<Category> findAll()
    {
        return categoryRepository.findAll();
    }
}
