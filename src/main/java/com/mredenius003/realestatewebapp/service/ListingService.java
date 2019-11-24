package com.mredenius003.realestatewebapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mredenius003.realestatewebapp.model.Listing;
import com.mredenius003.realestatewebapp.model.User;
import com.mredenius003.realestatewebapp.repository.ListingRepository;
import com.mredenius003.realestatewebapp.repository.UserRepository;

@Service("userService")
public class ListingService {

    private final UserRepository userRepository;
    private final ListingRepository listingRepository;

    @Autowired
    public ListingService(UserRepository userRepository, ListingRepository listingRepository) {
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
    }

    public Listing findByMls(long mls) {
        return listingRepository.findByMls(mls);
    }

    public Set<Listing> findByCity(String city) {
        return findByCity(city);
    }

    public Set<Listing> findByState(String state) {
        return listingRepository.findByState(state);
    }

    public Set<Listing> findByZipcode(String zipcode) {
        return listingRepository.findByZipcode(zipcode);
    }

    public Set<Listing> findByNumBedrooms(Integer numBedrooms) {
        return listingRepository.findByNumBedrooms(numBedrooms);
    }

    public Set<Listing> findByNumBathrooms(Integer numBathrooms) {
        return listingRepository.findByNumBathrooms(numBathrooms);
    }

    public Set<Listing> findByLotSize(Double lotSize) {
        return listingRepository.findByLotSize(lotSize);
    }

    public Set<Listing> findListinsByUser(User user) {
        Set<Listing> listings = new HashSet<Listing>();
        user.getListings().forEach(listing -> listings.add(listing));
        return listings;
    }

    public Listing saveListing(Listing listing) {
        return listingRepository.save(listing);
    }
}
