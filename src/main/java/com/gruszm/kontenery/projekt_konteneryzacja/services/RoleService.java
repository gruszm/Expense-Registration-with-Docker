package com.gruszm.kontenery.projekt_konteneryzacja.services;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.Role;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService
{
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void saveAll(Role ... roles)
    {
        for (Role r : roles)
        {
            roleRepository.save(r);
        }
    }

    public void save(Role role)
    {
        roleRepository.save(role);
    }

    public List<Role> findAll()
    {
        return roleRepository.findAll();
    }
}
