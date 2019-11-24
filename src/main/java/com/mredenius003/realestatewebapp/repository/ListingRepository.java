package com.mredenius003.realestatewebapp.repository;

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
public interface ListingRepository extends JpaRepository<Listing, Long> {

    Listing findByMls(long mls);

    Listing findByCity(String city);

    Listing findByState(String state);

    Listing findByZipcode(String zipcode);

    Listing findByNumBedrooms(Integer numBedrooms);

    Listing findByNumBathrooms(Integer numBathrooms);

    Listing findByLotSize(Double lotSize);
}