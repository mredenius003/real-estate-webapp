package com.mredenius003.realestatewebapp.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.mredenius003.realestatewebapp.model.Listing;
import com.mredenius003.realestatewebapp.repository.ListingRepository;
import com.mredenius003.realestatewebapp.repository.UserRepository;

/**
 * Unit tests for ListingService.
 * 
 * @author mrede003
 *
 */
public class ListingServiceTest {

    @Mock
    private ListingRepository mockListingRepository;
    @Mock
    private UserRepository mockUserRepository;

    private ListingService listingServiceUnderTest;
    private Listing listing;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        listingServiceUnderTest = new ListingService(mockUserRepository, mockListingRepository);
        Mockito.when(mockListingRepository.save(any())).thenReturn(listing);
    }

    @Test
    public void testSaveListing() {
        Listing result = listingServiceUnderTest.saveListing(listing);
        assertEquals(listing, result);
    }

    /**
     * TODO: Add unit tests for rest of {@link ListingService} Apis.
     */
//    public void testFindByMls() {
//        
//    }
//    
//    public void testFindByCity() {
//        
//    }
//    
//    public void testFindByState() {
//        
//    }
//    
//    public void testFindByZipcode() {
//        
//    }
//    
//    public void testFindByNumBedrooms() {
//        
//    }
//    
//    public void testFindByNumBathrooms() {
//        
//    }
//    
//    public void testFindByLotSize() {
//        
//    }
//    
//    public void testFindListingsByUser() {
//        
//    }
}
