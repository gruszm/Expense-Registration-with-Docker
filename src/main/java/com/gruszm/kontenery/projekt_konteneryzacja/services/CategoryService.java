package com.gruszm.kontenery.projekt_konteneryzacja.services;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.Category;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService
{
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void saveAll(Category... categories)
    {
        for (Category c : categories)
        {
            categoryRepository.save(c);
        }
    }

    public void save(Category category)
    {
        categoryRepository.save(category);
    }

    public List<Category> findAll()
    {
        return categoryRepository.findAll();
    }
}
