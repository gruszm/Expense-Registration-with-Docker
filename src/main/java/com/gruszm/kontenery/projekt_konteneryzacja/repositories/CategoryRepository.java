package com.gruszm.kontenery.projekt_konteneryzacja.repositories;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String>
{

}
