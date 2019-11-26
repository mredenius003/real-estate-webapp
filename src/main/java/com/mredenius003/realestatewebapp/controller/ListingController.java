package com.mredenius003.realestatewebapp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mredenius003.realestatewebapp.model.Listing;
import com.mredenius003.realestatewebapp.model.User;
import com.mredenius003.realestatewebapp.service.ListingService;
import com.mredenius003.realestatewebapp.service.UserService;

@Controller
public class ListingController {
    /**
     * Strings used to compare for the type of search selected.
     */
    private static final String MLS = "mls";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String ZIPCODE = "zipcode";
    private static final String BEDROOMS = "bedrooms";
    private static final String BATHROOMS = "bathrooms";
    private static final String SQUARE_FOOTAGE = "sqft";
    private static final Set<String> VALID_KEYS = new HashSet<String>();
    static {
        VALID_KEYS.add(MLS);
        VALID_KEYS.add(CITY);
        VALID_KEYS.add(STATE);
        VALID_KEYS.add(ZIPCODE);
        VALID_KEYS.add(BEDROOMS);
        VALID_KEYS.add(BATHROOMS);
        VALID_KEYS.add(SQUARE_FOOTAGE);
    }
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

    @RequestMapping(value = "/listings/viewUserListings", method = RequestMethod.GET)
    public ModelAndView viewUserListings() {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getActiveUser();
        modelAndView.addObject("listings", sortListingsByDate(listingService.findListingsByUser(user)));
        modelAndView.setViewName("listings/viewUserListings");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView viewSearch() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @RequestMapping(value = "/listings/editListing", method = RequestMethod.GET)
    public ModelAndView editListing(@RequestParam("listing") Optional<String> mls) {
        ModelAndView modelAndView = new ModelAndView();
        Listing listing = null;
        if (mls.isPresent()) {
            listing = listingService.findByMls(mls.get());
        } else {
            listing = new Listing();
        }
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
            if (listingService.findByMls(listing.getMls()) != null) {
                Listing oldListing = listingService.findByMls(listing.getMls());
                listing.setId(oldListing.getId());
                listing.setDateListed(oldListing.getDateListed());
            } else {
                listing.setDateListed(LocalDateTime.now());
            }
            listingService.saveListing(listing);

            modelAndView.addObject("successMessage", getListingSavedSuccessMessage(listing));
            modelAndView.setViewName("listings/editListing");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/listings/deleteListing", method = RequestMethod.POST)
    public ModelAndView deleteListing(@RequestParam("listing") String mls) {
        listingService.deleteByMls(mls);
        return viewUserListings();
    }

    @RequestMapping(value = "/viewListing", method = RequestMethod.POST)
    public ModelAndView viewListing(@RequestParam("listing") String mls) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listing", listingService.findByMls(mls));
        modelAndView.setViewName("viewListing");
        return modelAndView;
    }

    @RequestMapping(value = "/viewAllListings", method = RequestMethod.GET)
    public ModelAndView viewAllListings(@RequestParam("key") Optional<String> key,
            @RequestParam("search") Optional<String> search) {
        ModelAndView modelAndView = new ModelAndView();
        List<Listing> listings = null;

        // If there is a search resolve it.
        if (key.isPresent() && search.isPresent()) {
            listings = resolveSearch(key.get(), search.get());
        } else {

            // Else show all.
            listings = listingService.findAll();
        }
        modelAndView.addObject("listings", listings);
        modelAndView.setViewName("viewAllListings");
        return modelAndView;
    }

    private String getListingSavedSuccessMessage(Listing listing) {
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

    /**
     * Helper method to sort listings in chronological order.
     */
    private List<Listing> sortListingsByDate(Collection<Listing> collection) {
        List<Listing> listings = new ArrayList<Listing>(collection);

        // Sort by date added in chronological order.
        Collections.sort(listings, new Comparator<Listing>() {
            @Override
            public int compare(Listing arg0, Listing arg1) {
                return arg0.getDateListed().compareTo(arg1.getDateListed());
            }
        });
        return listings;
    }

    /*
     * Helper method to resolve a search for listings.
     */
    private List<Listing> resolveSearch(String key, String search) {
        List<Listing> listings = new ArrayList<Listing>();
        ;
        if (VALID_KEYS.contains(key)) {
            switch (key) {
            case MLS:
                listings.add(listingService.findByMls(search));
                break;
            case CITY:
                listings.addAll(listingService.findByCity(search));
                break;
            case STATE:
                listings.addAll(listingService.findByState(search));
                break;
            case ZIPCODE:
                listings.addAll(listingService.findByZipcode(search));
                break;
            case BEDROOMS:
                if (tryParse(search) != null) {
                    listings.addAll(listingService.findByNumBedrooms(tryParse(search)));
                    break;
                }
            case BATHROOMS:
                if (tryParse(search) != null) {
                    listings.addAll(listingService.findByNumBathrooms(tryParse(search)));
                    break;
                }
            case SQUARE_FOOTAGE:
                if (tryParse(search) != null) {
                    listings.addAll(listingService.findBySize(tryParse(search)));
                    break;
                }
            default:
                return sortListingsByDate(listingService.findAll());
            }
        }
        return sortListingsByDate(listingService.findAll());
    }

    private Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
