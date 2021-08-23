package com.topteer.topteer.controllers;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.User;
import com.topteer.topteer.repositories.EventRepository;
import com.topteer.topteer.repositories.EventVolunteerRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import com.topteer.topteer.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventVolunteerController {
    private EventRepository eventDao;
    private UserRepository userDao;
    private EventVolunteerRepo EVRepo;

    public EventVolunteerController( EventRepository eventDao, UserRepository userDao, EventVolunteerRepo EVRepoDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.EVRepo = EVRepoDao;
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
        return "redirect:/event/" + id + "/show";
    }
}
