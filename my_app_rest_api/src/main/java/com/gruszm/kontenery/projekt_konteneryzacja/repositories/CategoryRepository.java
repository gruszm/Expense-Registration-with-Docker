package com.gruszm.kontenery.projekt_konteneryzacja.repositories;

import com.gruszm.kontenery.entities.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String>
{
    Category findByName(String name);
}
