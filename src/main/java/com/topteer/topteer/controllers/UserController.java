package com.topteer.topteer.controllers;

import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {

    private UserRepository users;

    public UserController(UserRepository users) {
        this.users = users;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }
}
