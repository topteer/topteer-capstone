
//package com.topteer.topteer.controllers;
//
//
//import com.topteer.topteer.models.OrgCoord;
//import com.topteer.topteer.models.User;
//import com.topteer.topteer.repositories.OrgCoordRepository;
//import com.topteer.topteer.repositories.OrganizationRepository;
//import com.topteer.topteer.repositories.UserRepository;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//
//
//
//@Controller
//public class OrgCoordController {
//    private OrganizationRepository orgDao;
//    private UserRepository userDao;
//    private OrgCoordRepository orgCoordDao;
//
//    public OrgCoordController(OrganizationRepository orgDao, UserRepository userDao, OrgCoordRepository orgCoordDao){
//        this.orgDao = orgDao;
//        this.userDao = userDao;
//        this.orgCoordDao = orgCoordDao;
//    }
//
//    public String createList(){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        OrgCoord orgCoord = orgCoordDao.findByUserId(currentUser.getId());
//        long orgId =
//        long eCoord = currentUser.getId();
//        OrgCoord orgCoord = new OrgCoord();
//        orgCoordDao.save(orgCoord);
//    }
//
//}

