package com.mredenius003.realestatewebapp.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mredenius003.realestatewebapp.model.Listing;
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
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/listings/viewAllListings", method = RequestMethod.GET)
    public ModelAndView showAllListings() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("listings/viewAllListings");
        return modelAndView;
    }

    @RequestMapping(value = "/listings/viewUserListings", method = RequestMethod.GET)
    public ModelAndView showUserListings() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("listings/viewUserListings");
        return modelAndView;
    }

    @RequestMapping(value = "/listings/editListing", method = RequestMethod.GET)
    public ModelAndView editListing() {
        ModelAndView modelAndView = new ModelAndView();
        Listing listing = new Listing();
        modelAndView.addObject("listing", listing);
        modelAndView.setViewName("listings/editListing");
        return modelAndView;
    }

    @RequestMapping(value = "/listings/editListing", method = RequestMethod.POST)
    public ModelAndView saveListing(@Valid Listing listing, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("listings/editListing");
        } else {
            listing.setDateListed(LocalDateTime.now());
            listingService.saveListing(listing);

            modelAndView.addObject("successMessage", getSuccessMessage(listing));
            modelAndView.setViewName("listings/editListing");
        }
        return modelAndView;
    }

    private String getSuccessMessage(Listing listing) {
        StringBuilder builder = new StringBuilder();
        builder.append(listing.getStreet());
        builder.append(" ");
        if (listing.getStreet2() != null) {
            builder.append(listing.getStreet2());
            builder.append(" ");
        }
        builder.append("has been saved successfully!");
        return builder.toString();
    }
}
