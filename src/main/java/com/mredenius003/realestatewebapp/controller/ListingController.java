package com.mredenius003.realestatewebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mredenius003.realestatewebapp.model.User;
import com.mredenius003.realestatewebapp.service.ListingService;
import com.mredenius003.realestatewebapp.service.UserService;

@Controller
public class ListingController {
    @Autowired
    private UserService userService;

    @Autowired
    private ListingService listingService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView showUserData() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName",
                "Welcome " + user.getName() + " " + user.getLastName());
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
