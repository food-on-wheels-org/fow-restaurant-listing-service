package com.project.tejas.restaurantlisting.service;

import com.project.tejas.restaurantlisting.dto.RestaurantListingDTO;
import com.project.tejas.restaurantlisting.entity.RestaurantListing;
import com.project.tejas.restaurantlisting.mapper.RestaurantListingMapper;
import com.project.tejas.restaurantlisting.repo.RestaurantListingRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantListingServiceTest {

    @Mock
    RestaurantListingRepo restaurantListingRepo;

    @InjectMocks
    RestaurantListingService restaurantListingService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRestaurants(){
        List<RestaurantListing> mockRestaurantList = Arrays.asList(
                new RestaurantListing(1, "Restaurant 1", "Address 1", "city 1", "Description 1"),
                new RestaurantListing(2, "Restaurant 2", "Address 2", "city 2", "Description 2")
        );
        when(restaurantListingRepo.findAll()).thenReturn(mockRestaurantList);

        List<RestaurantListingDTO> restaurantListingDTOList = restaurantListingService.findAllRestaurants();

        assertEquals(mockRestaurantList.size(), restaurantListingDTOList.size());
        for (int i = 0; i < mockRestaurantList.size(); i++){
            RestaurantListingDTO expectedDTO = RestaurantListingMapper.INSTANCE.mapRestauranttoRestaurantDTO(mockRestaurantList.get(i));
            assertEquals(expectedDTO, restaurantListingDTOList.get(i));
        }

        verify(restaurantListingRepo, times(1)).findAll();
    }

    @Test
    public void testAddRestaurantToDb(){
        RestaurantListingDTO mockAddedRestaurantDTO = new RestaurantListingDTO(1, "Restaurant 1", "Address 1", "city 1", "Description 1");
        RestaurantListing mockAddedRestaurant = RestaurantListingMapper.INSTANCE.mapRestaurantDTOtoRestaurant(mockAddedRestaurantDTO);

        //Just mock the repository's behavior: Takes an entity, then returns an entity
        when(restaurantListingRepo.save(mockAddedRestaurant)).thenReturn(mockAddedRestaurant);

        RestaurantListingDTO savedRestaurantDTO = restaurantListingService.addRestaurantToDb(mockAddedRestaurantDTO);

        assertEquals(mockAddedRestaurantDTO, savedRestaurantDTO);

        verify(restaurantListingRepo, times(1)).save(mockAddedRestaurant);

    }

    @Test
    public void testFindRestaurantById(){
        Integer mockId = 1;
        RestaurantListing mockRestaurant = new RestaurantListing(1, "Restaurant 1", "Address 1", "city 1", "Description 1");
        when(restaurantListingRepo.findById(mockId)).thenReturn(Optional.of(mockRestaurant));

        ResponseEntity<RestaurantListingDTO> response = restaurantListingService.findRestaurantById(mockId);

        assertEquals(mockId, response.getBody().getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(restaurantListingRepo, times(1)).findById(mockId);
    }

    @Test
    public void testFindRestaurantById_NonExistingID(){
        Integer mockId = 1;
        RestaurantListing mockRestaurant = new RestaurantListing(1, "Restaurant 1", "Address 1", "city 1", "Description 1");
        when(restaurantListingRepo.findById(mockId)).thenReturn(Optional.empty());

        ResponseEntity<RestaurantListingDTO> response = restaurantListingService.findRestaurantById(mockId);

        assertEquals(null, response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(restaurantListingRepo, times(1)).findById(mockId);
    }
}
