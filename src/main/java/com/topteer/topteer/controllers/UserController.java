package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.Organization;
import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.OrganizationRepository;
import com.topteer.topteer.repositories.UserRepository;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private UserRepository usersDao;
    private OrganizationRepository orgDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder, OrganizationRepository orgDao) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
        this.orgDao = orgDao;

    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, @RequestParam(name = "password") String password, @RequestParam(name = "password-confirm") String confirm){
        if (!password.equals(confirm)) {
            return "redirect:/register";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";
    }


    @GetMapping("/users/edit")
    public String showUserEditForm(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/users/edit")
    public String saverUserUpdate(@ModelAttribute User user,@RequestParam(name = "firstName") String firstName,@RequestParam(name = "lastName") String lastName, @RequestParam(name = "username") String username, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password, @RequestParam(name = "password-confirm") String confirm) {
        if (!password.equals(confirm)) {
            return "redirect:/users/edit";
        }
        String hash = passwordEncoder.encode(password);
        user.setPassword(hash);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        usersDao.save(user);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setUsername(username);
        userDetails.setEmail(email);
        userDetails.setPassword(hash);
        return "redirect:/users/profile";
    }

    @GetMapping("/users/profile")
    public String getProfilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = usersDao.getById(user.getId());
        long userId = user.getId();
        Organization organization = orgDao.findByUserId(userId);
        String orgN = "You currently don't own an organization";
        if(organization != null) {
            orgN = organization.getOrgName();
        }
        long orgId = 0;
        if(organization!= null)
        {
            orgId=organization.getId();
        }
        model.addAttribute("orgN", orgN);
        model.addAttribute("usersOrgId",orgId);
        model.addAttribute("user", user);
        return "users/profile";
    }

    @GetMapping("/users/{id}/profile")
    public String getOtherProfilePage(Model model, @PathVariable long id) {
        User user = usersDao.getById(id);
        model.addAttribute("user", user);
        return "users/profile";
    }

}
