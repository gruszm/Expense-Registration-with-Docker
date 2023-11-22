package com.gruszm.kontenery.projekt_konteneryzacja.services;

import com.gruszm.kontenery.entities.Expense;
import com.gruszm.kontenery.entities.User;
import com.gruszm.kontenery.projekt_konteneryzacja.exceptions.UserAlreadyExistsException;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.ExpenseRepository;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private UserRepository userRepository;
    private ExpenseRepository expenseRepository;

    @Autowired
    public UserService(UserRepository userRepository, ExpenseRepository expenseRepository)
    {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    @Transactional
    public void saveAll(User... users) throws UserAlreadyExistsException
    {
        for (User u : users)
        {
            if (userRepository.findByEmail(u.getEmail()) != null)
            {
                throw new UserAlreadyExistsException("User already exists in the database: " + u.getEmail());
            }

            userRepository.save(u);
        }
    }

    public void deleteByEmail(String email)
    {
        User user = userRepository.findByEmail(email);

        List<Expense> usersExpenses = expenseRepository.findAllByUser(user);
        expenseRepository.deleteAll(usersExpenses);

        userRepository.delete(user);
    }

    public void deleteById(String id)
    {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent())
        {
            List<Expense> usersExpenses = expenseRepository.findAllByUser(user.get());
            expenseRepository.deleteAll(usersExpenses);

            userRepository.deleteById(id);
        }
    }

    public void save(User user) throws UserAlreadyExistsException
    {
        if (userRepository.findByEmail(user.getEmail()) != null)
        {
            throw new UserAlreadyExistsException("User already exists in the database: " + user.getEmail());
        }

        userRepository.save(user);
    }

    public User findById(String id)
    {
        Optional<User> user = userRepository.findById(id);

        return user.isPresent() ? user.get() : null;
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
