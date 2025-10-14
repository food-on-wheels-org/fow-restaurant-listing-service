package com.project.tejas.restaurantlisting.controller;

import com.project.tejas.restaurantlisting.dto.RestaurantListingDTO;
import com.project.tejas.restaurantlisting.service.RestaurantListingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantListingControllerTest {

    @InjectMocks
    RestaurantListingController restaurantListingController;

    @Mock
    RestaurantListingService restaurantListingService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAllRestaurants(){
        List<RestaurantListingDTO> mockRestaurants = Arrays.asList(
                new RestaurantListingDTO(1, "Restaurant 1", "Address 1", "city 1","Description 1"),
                new RestaurantListingDTO(2, "Restaurant 2", "Address 2", "city 2", "Description 2")
        );
        when(restaurantListingService.findAllRestaurants()).thenReturn(mockRestaurants);

        ResponseEntity<List<RestaurantListingDTO>> response = restaurantListingController.fetchAllRestaurants();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurants, response.getBody());
        verify(restaurantListingService, times(1)).findAllRestaurants();
    }

    @Test
    public void testSaveRestaurant(){
        RestaurantListingDTO mockAddedRestaurant = new RestaurantListingDTO(1, "Restaurant 1", "Address 1", "city 1", "Description 1");
        when(restaurantListingService.addRestaurantToDb(mockAddedRestaurant)).thenReturn(mockAddedRestaurant);

        ResponseEntity<RestaurantListingDTO> response = restaurantListingController.saveRestaurant(mockAddedRestaurant);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockAddedRestaurant, response.getBody());
        verify(restaurantListingService, times(1)).addRestaurantToDb(mockAddedRestaurant);
    }

    @Test
    public void testFetchRestaurant(){
        Integer mockId = 1;
        RestaurantListingDTO mockRestaurant = new RestaurantListingDTO(1, "Restaurant 1", "Address 1", "city 1", "Description 1");
        when(restaurantListingService.findRestaurantById(mockId)).thenReturn(new ResponseEntity<>(mockRestaurant, HttpStatus.OK));

        ResponseEntity<RestaurantListingDTO> response = restaurantListingController.fetchRestaurant(mockId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());
        verify(restaurantListingService, times(1)).findRestaurantById(mockId);
    }
}
