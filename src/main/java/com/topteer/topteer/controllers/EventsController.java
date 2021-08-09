package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.repositories.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventsController {

    private EventRepository event;

    @GetMapping("/register")
    public String showEventForm(Model model){
        model.addAttribute("events", new Events());
        return "events/register";
    }

    @PostMapping("/register")
    public String saveEvent(@ModelAttribute Events event){
        event.save(event);
        return "redirect:/profile";
    }
}

//find by title suggested by Jeff, scrapped previous work.
