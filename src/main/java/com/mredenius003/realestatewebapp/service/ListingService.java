package com.mredenius003.realestatewebapp.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mredenius003.realestatewebapp.model.Listing;
import com.mredenius003.realestatewebapp.model.User;
import com.mredenius003.realestatewebapp.repository.ListingRepository;

@Service("listingService")
public class ListingService {

    @Autowired
    private final UserService userService;
    private final ListingRepository listingRepository;

    @Autowired
    public ListingService(ListingRepository listingRepository, UserService userService) {
        this.listingRepository = listingRepository;
        this.userService = userService;
    }

    public Listing findByMls(String mls) {
        return listingRepository.findByMls(mls);
    }

    public void deleteByMls(String mls) {
        listingRepository.delete(findByMls(mls));
    }

    public List<Listing> findAll() {
        return Collections.unmodifiableList(listingRepository.findAll());
    }

    public Set<Listing> findByCity(String city) {
        return Collections.unmodifiableSet(listingRepository.findByCity(city));
    }

    public Set<Listing> findByState(String state) {
        return Collections.unmodifiableSet(listingRepository.findByState(state));
    }

    public Set<Listing> findByZipcode(String zipcode) {
        return Collections.unmodifiableSet(listingRepository.findByZipcode(zipcode));
    }

    public Set<Listing> findByNumBedrooms(Integer numBedrooms) {
        return Collections.unmodifiableSet(listingRepository.findByNumBedrooms(numBedrooms));
    }

    public Set<Listing> findByNumBathrooms(Integer numBathrooms) {
        return Collections.unmodifiableSet(listingRepository.findByNumBathrooms(numBathrooms));
    }

    public Set<Listing> findByLotSize(Double lotSize) {
        return Collections.unmodifiableSet(listingRepository.findByLotSize(lotSize));
    }

    public Set<Listing> findListingsByUser(User user) {
        Set<Listing> listings = new HashSet<Listing>();
        user.getMlsIds().forEach(id -> {
            Listing listing = listingRepository.findByMls(id);
            if (listing != null) {
                listings.add(listing);
            }
        });
        return Collections.unmodifiableSet(listings);
    }

    public Listing saveListing(Listing listing) {
        if (userService.getActiveUser() == null) {
        } else if (!userService.getActiveUser().getMlsIds().contains(listing.getMls())) {
            User activeUser = userService.getActiveUser();
            activeUser.getMlsIds().add(listing.getMls());
            userService.updateUser(activeUser);
        }
        return listingRepository.save(listing);
    }
}
