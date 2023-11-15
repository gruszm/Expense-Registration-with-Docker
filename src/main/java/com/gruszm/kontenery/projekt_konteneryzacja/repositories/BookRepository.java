package com.gruszm.kontenery.projekt_konteneryzacja.repositories;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String>
{

}
