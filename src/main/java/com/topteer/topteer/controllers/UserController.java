package com.topteer.topteer.controllers;

import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {

    private UserRepository usersDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao) {
        this.usersDao = usersDao;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";
    }
}
