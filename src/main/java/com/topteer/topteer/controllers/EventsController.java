package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.Organization;
import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.EventRepository;
import com.topteer.topteer.repositories.OrganizationRepository;
import com.topteer.topteer.repositories.UserRepository;
import com.topteer.topteer.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventsController {

    private EventRepository eventDao;
    private OrganizationRepository orgDao;
    private UserRepository userDao;
    private final EmailService emailService;

    public EventsController(EventRepository eventDao, OrganizationRepository orgDao, UserRepository userDao, EmailService emailService) {
        this.eventDao = eventDao;
        this.orgDao = orgDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
    public String saveEvent(@RequestParam long orgId, @RequestParam String title, @RequestParam long eCoordId, @RequestParam String description, @RequestParam String phone, @RequestParam String date, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String location, @RequestParam double hours, @Valid Events validEvent, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("events", validEvent);
            return "/event/create";
        }else{
            Organization org = orgDao.getById(orgId);
            User user = userDao.getById(eCoordId);

            Events event = new Events(org, title, description, user, phone, date, startTime, endTime, location, hours);
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
        boolean alreadyRegistered = false;
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long thisUser = currentUser.getId();
        List<User> eventUser = eventDao.getById(id).getEventvolunteer();

        for (User volunteer : eventUser){
            if(volunteer.getId() == thisUser){
                alreadyRegistered = true;
            }
        }
        model.addAttribute("alreadyRegistered", alreadyRegistered);
        String eventCoord = events.getUser().getFirstName();
        model.addAttribute("event", events);
        model.addAttribute("eCoord", eventCoord);
        model.addAttribute("isEventOwner", isEventOwner);
        model.addAttribute("currentUser", thisUser);
        return "event/show";
    }

    @PostMapping("/event/{id}/delete")
    public String deleteEvent(@PathVariable long id){
        Events eventFromDb = eventDao.getById(id);
        eventFromDb.setEventvolunteer(new ArrayList<User >());
        eventDao.save(eventFromDb);
        eventDao.delete(eventFromDb);
        return "redirect:/event";
    }
    @PostMapping("/event/{id}/register")
    public String eventRegister(@PathVariable long id){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        currentUser = userDao.getById(currentUser.getId());
        Events event = eventDao.getById(id);
        List<User> eventsUser = event.getEventvolunteer();
        eventsUser.add(currentUser);
        event.setEventvolunteer(eventsUser);
        eventDao.save(event);
        String message = "Thank you for volunteering for " + event.getTitle() + " at " + event.getLocation() +" on " + event.getDate() + ". If you have any questions please feel free to contact " + event.getUser().getFirstName() + " at " + event.getPhone();
        try{
            emailService.sendRegVerify(userDao.getById(currentUser.getId()),message);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:/users/profile";
    }

    @PostMapping("/sendText/to/{id}")
    public String send(@PathVariable long id) throws IOException {
        try {
            emailService.sendTextEmail(userDao.getById(id));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/users/profile";
    }
}