package com.gruszm.kontenery.unused.repositories;

import com.gruszm.kontenery.unused.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String>
{

}
