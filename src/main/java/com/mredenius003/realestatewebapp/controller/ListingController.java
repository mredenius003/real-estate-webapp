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

//    @RequestMapping(value = "/listing/add", method = RequestMethod.POST)
//    public ModelAndView createNewListing(@Valid User user, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        User userExists = userService.findUserByEmail(user.getEmail());
//        if (userExists != null) {
//            bindingResult.rejectValue("email", "error.user",
//                    "There is already a user registered with the email provided");
//        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("registration");
//        } else {
//            userService.saveUser(user);
//            modelAndView.addObject("successMessage", "User has been registered successfully");
//            modelAndView.addObject("user", new User());
//            modelAndView.setViewName("registration");
//
//        }
//        return modelAndView;
//    }
}
