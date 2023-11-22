package com.gruszm.kontenery.projekt_konteneryzacja.repositories;

import com.gruszm.kontenery.entities.Category;
import com.gruszm.kontenery.entities.Expense;
import com.gruszm.kontenery.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String>
{
    List<Expense> findAllByCategory(Category category);
    List<Expense> findAllByUser(User user);
}
