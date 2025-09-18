package com.project.tejas.restaurantlisting.controller;

import com.project.tejas.restaurantlisting.dto.RestaurantListingDTO;
import com.project.tejas.restaurantlisting.service.RestaurantListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantListingController {
    @Autowired
    RestaurantListingService restaurantListingService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantListingDTO>> fetchAllRestaurants(){
        List<RestaurantListingDTO> allRestaurants = restaurantListingService.findAllRestaurants();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantListingDTO> saveRestaurant(@RequestBody RestaurantListingDTO restaurantListingDTO){
        RestaurantListingDTO addedRestaurant = restaurantListingService.addRestaurantToDb(restaurantListingDTO);
        return new ResponseEntity<>(addedRestaurant, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantById/{id}")
    public ResponseEntity<RestaurantListingDTO> fetchRestaurant(@PathVariable Integer id){
        return restaurantListingService.findRestaurantById(id);
    }
}
