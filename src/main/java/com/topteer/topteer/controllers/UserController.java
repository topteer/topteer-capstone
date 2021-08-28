package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Organization;
import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.OrganizationRepository;
import com.topteer.topteer.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        boolean nullOrg = false;
        if(organization != null) {
            orgN = organization.getOrgName();
            nullOrg = true;
        }
        long orgId = 0;
        if(organization!= null)
        {
            orgId=organization.getId();
        }
        model.addAttribute("nullOrg", nullOrg);
        model.addAttribute("orgN", orgN);
        model.addAttribute("usersOrgId",orgId);
        model.addAttribute("user", user);
        return "users/profile";
    }

    @GetMapping("/users/{id}/profile")
    public String getOtherProfilePage(Model model, @PathVariable long id) {
        User user = usersDao.getById(id);
        long userId = user.getId();
        String orgName = "User doesn't have own a organization.";
        Organization organization = orgDao.findByUserId(userId);
        boolean nullOrg = false;
        if(organization != null) {
            orgName = organization.getOrgName();
            nullOrg = true;
        }
        model.addAttribute("nullOrg", nullOrg);
        model.addAttribute("orgName", orgName);
        model.addAttribute("user", user);
        return "users/profile";
    }

}
