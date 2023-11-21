package com.gruszm.kontenery.projekt_konteneryzacja.services;

import com.gruszm.kontenery.entities.User;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService
{
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveAll(User... users)
    {
        for (User u : users)
        {
            userRepository.save(u);
        }
    }

    public void deleteByEmail(String email)
    {
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
    }

    public void deleteById(String id)
    {
        userRepository.deleteById(id);
    }

    public void save(User user)
    {
        userRepository.save(user);
    }

    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }
}
