package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.EventRepository;
import com.topteer.topteer.repositories.EventVolunteerRepository;
import com.topteer.topteer.repositories.OrganizationRepository;
import jdk.jfr.Event;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import com.topteer.topteer.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventVolunteerController {
    private EventRepository eventDao;
    private UserRepository userDao;
    private EventVolunteerRepository EVRepoDao;

    public EventVolunteerController(EventVolunteerRepository EVRepoDao, EventRepository eventDao, UserRepository userDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.EVRepoDao = EVRepoDao;
    }

    @GetMapping("/event/{id}/register")
    public String getRegisterButton(@PathVariable long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserID = currentUser.getId();
        model.addAttribute("currentUserID", currentUserID);
//
        model.addAttribute("eventID", id);
        return "event/show";
    }


    @PostMapping("/event/{id}/register")
    public String asd(@PathVariable long id, Model model) {
        return "search/index";
    }

}
