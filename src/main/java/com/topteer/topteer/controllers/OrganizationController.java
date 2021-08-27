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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    //     ======== Show all events ===========
    @GetMapping("/organizations")
    public String allOrgs(Model model) {
        model.addAttribute("organizations", orgDao.findAll());
        return "organization/index";
    }

    @GetMapping("/organization/{id}/show")
    private String singleOrg(@PathVariable long id, Model model){
        Organization org = orgDao.getById(id);
        Boolean isOrgOwner = false;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser"){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isOrgOwner = currentUser.getId() == org.getUser().getId();
        }
        model.addAttribute("org", org);
        model.addAttribute("isOrgOwner", isOrgOwner);
        return "organization/show";
    }

//    ========== Create organization ===============
    @GetMapping("/organization/create")
    private String showOrgForm(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Organization organization = orgDao.findByUserId(user.getId());
        if(organization == null)
        {
            model.addAttribute("organization", new Organization());
            return "organization/create";
        }
        else
        {
            return "redirect:/organization/"+organization.getId()+"/edit";
        }




    }
    @RequestMapping(value = "/organization/create", method = RequestMethod.POST)
    public String saveOrg(@RequestParam String orgName, @RequestParam String address, @RequestParam String city, @RequestParam String state, @RequestParam String zip, @RequestParam String phone, @RequestParam String email, @Valid Organization validOrg, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("orgs", validOrg);
            return "organization/create";
        }

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Organization organization = new Organization(currentUser,orgName, address, city, state, zip, phone, email);

        orgDao.save(organization);

        return "redirect:/organization/" + organization.getId() + "/show";
    }

//    ======== Edit organization =========
    @GetMapping("/organization/edit")
    public String orgEdit(Model model){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Organization organization = orgDao.findByUserId(user.getId());
            String state = organization.getState();
        model.addAttribute("orgs", organization);
            model.addAttribute("state", state);
            return "organization/edit";

    }

    @PostMapping("/organization/edit")
    public String orgEdit(@RequestParam(name = "address") String address, @RequestParam(name = "city") String city, @RequestParam(name = "zip")String zip, @RequestParam(name = "phone") String phone, @RequestParam(name = "email") String email){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Organization organization = orgDao.findByUserId(user.getId());

        Organization orgEdit = orgDao.getById(organization.getId());
        orgEdit.setAddress(address);
        orgEdit.setCity(city);
        orgEdit.setZip(zip);
        orgEdit.setPhone(phone);
        orgEdit.setEmail(email);
        orgDao.save(orgEdit);
        return "redirect:/organization/" + organization.getId() + "/show";
    }
}
