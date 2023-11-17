package com.gruszm.kontenery.projekt_konteneryzacja.repositories;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseRepository extends MongoRepository<Expense, String>
{

}
