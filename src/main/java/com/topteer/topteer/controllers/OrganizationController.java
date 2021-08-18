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
    private UserRepository userDao;

//    ========== Repository injection ============
    public OrganizationController(OrganizationRepository orgDao, UserRepository userDao) {
        this.orgDao = orgDao;
        this.userDao = userDao;
    }

    //     ======== Show all events ============
    @GetMapping("/organizations")
    public String allOrgs(Model model) {
        model.addAttribute("organizations", orgDao.findAll());
        return "/organization/index";
    }

//    ========== Create organization ===============
    @GetMapping("/organization/create")
    private String showOrgForm(Model model){
        model.addAttribute("organization", new Organization());
        return "/organization/create";
    }
    @RequestMapping(value = "/organization/create", method = RequestMethod.POST)
    public String saveOrg(@RequestParam String orgName, @RequestParam String address, @RequestParam String city, @RequestParam String state, @RequestParam String zip, @RequestParam String phone, @RequestParam String email, @Valid Organization validOrg, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("orgs", validOrg);
            return "/organization/create";
        }

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Organization organization = new Organization(currentUser,orgName, address, city, state, zip, phone, email);

        orgDao.save(organization);

        return "redirect:/profile";
    }

//    ======== Edit organization =========
    @GetMapping("/organization/{id}/edit")
    public String orgEdit(@PathVariable long id, Model model){
        Organization organization = orgDao.getById(id);
        //TODO: check if current logged in user id matches the user id for
        //this organization
        //if not, give error / warning / don't allow them to proceed
        //if so, show them the edit form so they can get back to work
        //same problem for events
        String state = organization.getState();
        model.addAttribute("state", state);
        model.addAttribute("orgs", organization);
            return "/organization/edit";
    }

    @PostMapping("/organization/{id}/edit")
    public String orgEdit(@PathVariable long id, @RequestParam(name = "address") String address, @RequestParam(name = "city") String city, @RequestParam(name = "zip")String zip, @RequestParam(name = "phone") String phone, @RequestParam(name = "email") String email){
        Organization orgEdit = orgDao.getById(id);
        orgEdit.setAddress(address);
        orgEdit.setCity(city);
        orgEdit.setZip(zip);
        orgEdit.setPhone(phone);
        orgEdit.setEmail(email);
        orgDao.save(orgEdit);
        return "redirect:/organization/" + id;
    }
}
