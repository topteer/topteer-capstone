package com.topteer.topteer.controllers;

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
    public String placeholder1(@PathVariable long id){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserID = currentUser.getId();
        EVRepo.save(e);

    }


//    @PostMapping("/event/{id}/register")
//    public String postRegisterButton(@RequestParam long eventID, @RequestParam long currentUserID) {
//        Events event = eventDao.getById(eventID);
//        User user = userDao.getById(currentUserID);
//        EVRepoDao.save(register);
//
//        return "redirect:/event";
//    }

}
