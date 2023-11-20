package com.gruszm.kontenery.projekt_konteneryzacja.repositories;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>
{
    User findByEmail(String email);
}
