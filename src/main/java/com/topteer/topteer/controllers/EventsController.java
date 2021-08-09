package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.repositories.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class EventsController {

    private EventRepository eventDao;

    @GetMapping("/create")
    public String showEventForm(Model model){
        model.addAttribute("events", new Events());
        return "event/create";
    }

    @RequestMapping(value = "/event/create",method = RequestMethod.POST)
    public String saveEvent(@RequestParam String title, @RequestParam String phone, @RequestParam String date, @RequestParam String time, @RequestParam String location, @RequestParam int hours, @RequestParam int length, @Valid Events validEvent, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("events", validEvent);
            return "/event/create";
        }else{
            long orgId = validEvent.getOrgID();
            long eCoordId = validEvent.geteCoordID();
            Events event = new Events(orgId, title, eCoordId, phone, date, time, location, hours);
        }
        eventDao.save(event);
        return "redirect:/profile";
    }
}
