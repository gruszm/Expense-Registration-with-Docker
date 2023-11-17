package com.gruszm.kontenery.projekt_konteneryzacja.repositories;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String>
{

}