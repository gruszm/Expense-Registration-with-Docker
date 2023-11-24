package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.entities.User;
import com.gruszm.kontenery.http.AdditionalHttpStatus;
import com.gruszm.kontenery.projekt_konteneryzacja.exceptions.UserAlreadyExistsException;
import com.gruszm.kontenery.projekt_konteneryzacja.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> getAllUsers()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(name = "email") String email)
    {
        User user = userService.findByEmail(email);

        if (user == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        try
        {
            userService.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        catch (UserAlreadyExistsException e)
        {
            return ResponseEntity.status(AdditionalHttpStatus.ALREADY_EXISTS).body(null);
        }
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<User> deleteUserByEmail(@PathVariable(name = "email") String email)
    {
        User user = userService.findByEmail(email);

        if (user == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            userService.deleteByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable(name = "id") String id)
    {
        User user = userService.findById(id);

        if (user == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else
        {
            userService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }
}
