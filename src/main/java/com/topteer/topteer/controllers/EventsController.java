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

    @GetMapping("/event/create")
    public String showEventForm(Model model){
        model.addAttribute("events", new Events());
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long eCoordId = currentUser.getId();
        System.out.println(eCoordId);
        Organization organization = orgDao.findByUserId(eCoordId);


        String orgName = organization.getOrgName();
        long orgId = organization.getId();
        model.addAttribute("eCoordId", eCoordId);
        model.addAttribute("eCoord", currentUser.getFirstName());
        model.addAttribute("orgName", orgName);
        model.addAttribute("orgId", orgId);

        return "event/create";
    }

    @RequestMapping(value = "/event/create",method = RequestMethod.POST)
    public String saveEvent(@RequestParam long orgId, @RequestParam String title, @RequestParam long eCoordId, @RequestParam String description, @RequestParam String phone, @RequestParam String date, @RequestParam String time, @RequestParam String location, @RequestParam double hours, @RequestParam double length, @Valid Events validEvent, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("events", validEvent);
            return "/event/create";
        }else{
            Organization org = orgDao.getById(orgId);
            User user = userDao.getById(eCoordId);

            Events event = new Events(org, title, description, user, phone, date, time, location, hours, length);
            eventDao.save(event);

        }

        return "redirect:/event";
    }
    @GetMapping("/event/{id}/show")
    public String singleEvent(@PathVariable long id, Model model){
        Events events = eventDao.getById(id);
        Boolean isEventOwner = false;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser"){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isEventOwner = currentUser.getId() == events.getUser().getId();
        }
        System.out.println(events.getId());
        String eventCoord = events.getUser().getFirstName();
        model.addAttribute("event", events);
        model.addAttribute("eCoord", eventCoord);
        model.addAttribute("isEventOwner", isEventOwner);
        return "event/show";
    }

    @PostMapping("/event/{id}/delete")
    public String deleteEvent(@PathVariable long id){
        eventDao.deleteById(id);
        return "redirect: /event";
    }

}