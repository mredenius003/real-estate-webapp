package com.mredenius003.realestatewebapp.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.mredenius003.realestatewebapp.model.Listing;
import com.mredenius003.realestatewebapp.model.User;

/**
 * Utils class to generate mock data objects for testing.
 * 
 * @author mrede003
 *
 */
public final class MockDataGenerator {

    private static final String FIRST_NAME = "Matt";
    private static final String LAST_NAME = "Redenius";
    private static final String EMAIL = "sumredhed567@boredmail.com";
    private static final String STREET = "3905 Pine Grove Landing";
    private static final String STREET_2 = "Apt. 303";
    private static final String CITY = "Virginia Beach";
    private static final String STATE = "Virginia";
    private static final String ZIPCODE = "23451";
    private static final String NEIGHBORHOOD = "Marina Shores";
    private static final LocalDateTime DATE_LISTED = LocalDateTime
            .ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
    private static final HashSet<String> IMAGE_PATHS = new HashSet<String>();
    private static final String IMAGE_PATH = "/matt/home/images/";
    private static final String DESCRIPTION = "This is one REALLY cool house!.";
    private static final Set<Listing> LISTINGS = new HashSet<Listing>();
    private static final Random RANDOM = new Random();
    static {
        for (int i = 0; i < 5; i++) {
            IMAGE_PATHS.add(IMAGE_PATH);
        }
        for (int i = 0; i < 8; i++) {
            LISTINGS.add(generateListing(i));
        }
    }

    public static User generateUser() {
        User user = new User();
        user.setName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        user.setListings(LISTINGS);
        return user;
    }

    public static Listing generateListing(int id) {
        Listing listing = new Listing();
        listing.setId(id);
        listing.setMls(RANDOM.nextLong());
        listing.setStreet(STREET);
        listing.setStreet2(STREET_2);
        listing.setCity(CITY);
        listing.setState(STATE);
        listing.setZipcode(ZIPCODE);
        listing.setNeighborhood(NEIGHBORHOOD);
        listing.setDateListed(DATE_LISTED);
        listing.setNumBedrooms(RANDOM.nextInt());
        listing.setImagePaths(IMAGE_PATHS);
        listing.setNumBathrooms(RANDOM.nextInt());
        listing.setGarageSize(RANDOM.nextInt());
        listing.setSquareFootage(RANDOM.nextInt());
        listing.setLotSize(RANDOM.nextDouble());
        listing.setDescription(DESCRIPTION);
        return new Listing();
    }
}
