package com.topteer.topteer.controllers;
import com.topteer.topteer.models.Organization;
import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.OrganizationRepository;
import com.topteer.topteer.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class OrganizationController {
    private OrganizationRepository orgDao;
    private UserRepository usersDao;

//    Repository injection
    public OrganizationController(OrganizationRepository orgDao, UserRepository usersDao) {
        this.orgDao = orgDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/organization/create")
    private String showOrgForm(Model model){
        model.addAttribute("organization", new Organization());
        return "/organization/create";
    }
    @RequestMapping(value = "/organization/create", method = RequestMethod.POST)
    public String saveOrg(@RequestParam String org_name, @RequestParam String address, @RequestParam String city, @RequestParam String state, @RequestParam String zip, @RequestParam String phone, @RequestParam String email, @Valid Organization validOrg, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("orgs", validOrg);
            return "/organization/create";
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userId = usersDao.getById(currentUser.getId());
        Organization organization = new Organization(org_name, address, city, state, zip, phone, email, userId);
        orgDao.save(organization);
        return "redirect:/profile";
    }

    @GetMapping("/organization/{id}/edit")
    public String orgEdit(@PathVariable long id, Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Organization organization = orgDao.getById(id);
        if(currentUser.getId() == organization.getUser().getID()){
            model.addAttribute("orgs", organization);
            return "/organization/edit";
        }else{
            return "redirect:/organization/" + id;
        }

    }

    @PostMapping("/organization/{id}/edit")
    public String orgEdit(@PathVariable long id, @RequestParam(name = "phone") String phone, @RequestParam(name = "email") String email){
        Organization orgEdit = orgDao.getById(id);
        orgEdit.setPhone(phone);
        orgEdit.setEmail(email);
        orgDao.save(orgEdit);
        return "redirect:/organization/" + id;
    }
}
