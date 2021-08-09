package com.topteer.topteer.controllers;
import com.topteer.topteer.models.Organization;
import com.topteer.topteer.repositories.OrganizationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrganizationController {
    private OrganizationRepository org;

    @GetMapping("/orgRegister")
    private String showOrgForm(Model model){
        model.addAttribute("organization", new Organization());
        return "/organization/orgRegister";
    }
    @PostMapping("/orgRegister")
    public String saveOrg(@ModelAttribute Organization organization){
        Organization.save(organization);
        return "redirect:/profile";
    }
}
