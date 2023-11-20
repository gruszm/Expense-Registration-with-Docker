package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.User;
import com.gruszm.kontenery.projekt_konteneryzacja.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.findAll();
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable(name = "email") String email)
    {
        return userService.findByEmail(email);
    }

    @PostMapping
    public void saveUser(@RequestBody User user)
    {
        userService.save(user);
    }

    @DeleteMapping("/email/{email}")
    public void deleteUserByEmail(@PathVariable(name = "email") String email)
    {
        userService.deleteByEmail(email);
    }

    @DeleteMapping("/id/{id}")
    public void deleteUserById(@PathVariable(name = "id") String id)
    {
        userService.deleteById(id);
    }
}
