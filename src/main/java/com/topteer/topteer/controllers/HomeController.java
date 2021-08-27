package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.Organization;
import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.EventRepository;
import com.topteer.topteer.repositories.OrganizationRepository;
import com.topteer.topteer.repositories.UserRepository;
import com.topteer.topteer.services.EmailService;
import jdk.jfr.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Collection;
import java.util.List;


@Controller
public class HomeController {

    private EventRepository eventDao;
    private OrganizationRepository orgDao;
    private UserRepository userDao;

    public HomeController(EventRepository eventDao, OrganizationRepository orgDao, UserRepository userDao) {
        this.eventDao = eventDao;
        this.orgDao = orgDao;
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String welcome() {
        return "home";
    }

    @GetMapping("/search")
    public String getSearchForm() {
        return "search/index";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam(name = "search-params") String params, @RequestParam(name = "query") String query) {
        List<Events> searchResults;
        List<Organization> searchResults2;
        switch (params) {
            //            ===== search for organization =========
            case ("2"):
                searchResults2 = orgDao.findAllByOrgsContainingQuery(query);
                model.addAttribute("searchResults2", searchResults2);
                break;
            //            ===== search for event =========
            case ("1"):
                searchResults = eventDao.findAllEventsByTitleOrLocationContaining(query);
                model.addAttribute("searchResults", searchResults);
                break;
        }
        return "search/results";
    }

    @GetMapping("/search/results")
    public String showResults(@ModelAttribute(name = "searchResults") ArrayList<Event> searchResults, @ModelAttribute(name = "searchResults2") ArrayList<Organization> searchResults2) {
        return "search/results";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }

}
