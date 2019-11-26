package com.mredenius003.realestatewebapp.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mredenius003.realestatewebapp.model.Listing;

/**
 * API to abstract the retrieval of a {@link Listing} from a database.
 * 
 * @author mredenius003
 *
 */
@Repository("listingRepository")
public interface ListingRepository extends JpaRepository<Listing, Integer> {

    Listing findByMls(String mls);

    Set<Listing> findByCity(String city);

    Set<Listing> findByState(String state);

    Set<Listing> findByZipcode(String zipcode);

    Set<Listing> findByNumBedrooms(Integer numBedrooms);

    Set<Listing> findByNumBathrooms(Integer numBathrooms);

    Set<Listing> findByHomeSize(Integer size);
}