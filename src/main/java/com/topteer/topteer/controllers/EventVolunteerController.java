package com.topteer.topteer.controllers;

import com.topteer.topteer.repositories.EventRepository;
import org.springframework.stereotype.Controller;
import com.topteer.topteer.repositories.UserRepository;

@Controller
public class EventVolunteerController {
    private EventRepository eventDao;
    private UserRepository userDao;
}
