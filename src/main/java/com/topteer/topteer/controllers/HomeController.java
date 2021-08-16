package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.Organization;
import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.EventRepository;
import com.topteer.topteer.repositories.OrganizationRepository;
import com.topteer.topteer.repositories.UserRepository;
import jdk.jfr.Event;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
//            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
//            if (user.isBanned()) {
//                return "redirect:/you-got-banned";
//            }
//        }
        return "search/index";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam(name = "search-params") String params, @RequestParam (name = "query") String query) {
        List<Events> searchResults;
        List<Organization> searchResults2;
        switch (params) {
            //            ===== search for organization =========
            case ("2"):
                searchResults2 = orgDao.findAllByTitleContaining(query);
//                for (Organization result : searchResults) {
//                    System.err.println(result.getOrg_name());
//                }
                model.addAttribute("searchResults", searchResults2);
                break;
            //            ===== search for event =========
            case ("1"):
                searchResults = eventDao.findAllByTitleContaining(query);
//                for (Events result : searchResults) {
//                    System.err.println(result.getTitle());
//                }
                model.addAttribute("searchResults", searchResults);
                break;


//                ====== Default cause i dont know what im doing yet ======
            default:
                searchResults = eventDao.findAllByTitleContaining(query);
                List<User> usersMaster = userDao.findAllByUsernameContaining(query);
                for (User user : usersMaster) {
                    searchResults.addAll(eventDao.findAllByUserId(user.getId()));
                }

//                ===== Not familiar with sets and hashsets. need to read more ====
                Set<Events> holdUp = new HashSet<>(searchResults);
                searchResults = new ArrayList<>(holdUp);
                model.addAttribute("searchResults", searchResults);
                break;
        }
        return "search/results";
    }

    @GetMapping("/search/results")
    public String showResults(@ModelAttribute(name = "searchResults") ArrayList<Event> searchResults) {
//        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
//            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        }
        return "search/results";
    }
}
