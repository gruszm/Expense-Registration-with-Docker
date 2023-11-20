package com.gruszm.kontenery.unused.controllers;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.User;
import com.gruszm.kontenery.projekt_konteneryzacja.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mock")
public class MockController
{
    private UserService userService;

    @Autowired
    public MockController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/changeuser")
    public String mockChangeUser()
    {
        List<User> us = userService.findAll();
        User u = us.get(0);
        u.setFirstName("firstname_changed");
        userService.saveAll(u);
        return "Done";
    }
}
