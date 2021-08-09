package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.EventRepository;
import com.topteer.topteer.repositories.OrgCoordRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class EventsController {

    private EventRepository eventDao;
    private OrgCoordRepository orgCoordDao;

    public EventsController(EventRepository eventDao, OrgCoordRepository orgCoordDao) {
        this.eventDao = eventDao;
        this.orgCoordDao = orgCoordDao;
    }

    @GetMapping("/create")
    public String showEventForm(Model model){
        model.addAttribute("events", new Events());
        return "event/create";
    }

    @RequestMapping(value = "/event/create",method = RequestMethod.POST)
    public String saveEvent(@RequestParam String title, @RequestParam String phone, @RequestParam String date, @RequestParam String time, @RequestParam String location, @RequestParam double hours, @RequestParam double length, @Valid Events validEvent, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("events", validEvent);
            return "/event/create";
        }else{
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long orgId = orgCoordDao.getOrgID();
            long eCoordId = currentUser.getId();
            Events event = new Events(orgId, title, eCoordId, phone, date, time, location, hours, length);
            eventDao.save(event);
        }

        return "redirect:/profile";
    }
}

//find by title suggested by Jeff, scrapped previous work.
