package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.Organization;
import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.EventRepository;
import com.topteer.topteer.repositories.OrganizationRepository;
import com.topteer.topteer.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class EventsController {

    private EventRepository eventDao;
    private OrganizationRepository orgDao;
    private UserRepository userDao;

    public EventsController(EventRepository eventDao, OrganizationRepository orgDao, UserRepository userDao) {
        this.eventDao = eventDao;
        this.orgDao = orgDao;
        this.userDao = userDao;
    }

//    Show all events
    @GetMapping("/event")
    public String posts(Model model) {
        model.addAttribute("events", eventDao.findAll());
        return "event/index";
    }

    @GetMapping("/create")
    public String showEventForm(Model model){

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long eCoordId = currentUser.getId();
        Organization organization = orgDao.findByUserId(eCoordId);
        String org_name = organization.getOrgName();
        model.addAttribute("eCoordId", eCoordId);
        model.addAttribute("eCoord", currentUser.getFirstName());
        model.addAttribute("org_name", org_name);
        model.addAttribute("orgId", organization.getId());
        model.addAttribute("events", new Events());

        return "event/create";
    }

    @RequestMapping(value = "/event/create",method = RequestMethod.POST)
    public String saveEvent(@RequestParam long orgId, @RequestParam String title, @RequestParam long eCoordId, @RequestParam String description, @RequestParam String phone, @RequestParam String date, @RequestParam String time, @RequestParam String location, @RequestParam double hours, @RequestParam double length, @Valid Events validEvent, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("events", validEvent);
            return "/event/create";
        }else{


            Events event = new Events(orgId, title, description, eCoordId, phone, date, time, location, hours, length);
            eventDao.save(event);

        }

        return "redirect:/profile";
    }
    @GetMapping("/event/{id}/show")
    public String singleEvent(@PathVariable long id, Model model){
        Events events = eventDao.getById(id);
        String eventCoord = events.getUser().getFirstName();
        model.addAttribute("event", events);
        model.addAttribute("eCoord", eventCoord);
        return "event/show";
    }

}

//find by title suggested by Jeff, scrapped previous work.
