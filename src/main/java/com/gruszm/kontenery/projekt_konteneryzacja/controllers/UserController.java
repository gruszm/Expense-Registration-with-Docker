package com.gruszm.kontenery.projekt_konteneryzacja.controllers;

import com.gruszm.kontenery.projekt_konteneryzacja.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController
{
    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model)
    {
        model.addAttribute("users", userService.findAll());

        return "users/show-users";
    }
}
