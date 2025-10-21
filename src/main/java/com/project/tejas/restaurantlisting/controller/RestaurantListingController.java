package com.project.tejas.restaurantlisting.controller;

import com.project.tejas.restaurantlisting.dto.RestaurantListingDTO;
import com.project.tejas.restaurantlisting.service.RestaurantListingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
@Tag(name = "Restaurant Listing management", description = "Endpoints for managing restaurant listings")
public class RestaurantListingController {
    @Autowired
    RestaurantListingService restaurantListingService;

    @Operation(summary = "Get all restaurants", description = "Returns a list of all available restaurants")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully fetched restaurant list",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantListingDTO.class)))
    })
    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantListingDTO>> fetchAllRestaurants(){
        List<RestaurantListingDTO> allRestaurants = restaurantListingService.findAllRestaurants();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @Operation(summary = "Add a new restaurant", description = "Creates a new restaurant entry in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Restaurant successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantListingDTO.class)))
    })
    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantListingDTO> saveRestaurant(@RequestBody RestaurantListingDTO restaurantListingDTO){
        RestaurantListingDTO addedRestaurant = restaurantListingService.addRestaurantToDb(restaurantListingDTO);
        return new ResponseEntity<>(addedRestaurant, HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch restaurant by ID", description = "Returns details of a restaurant fetched by it's unique ID from database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurant found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantListingDTO.class)))
    })
    @GetMapping("/fetchRestaurantById/{id}")
    public ResponseEntity<RestaurantListingDTO> fetchRestaurant(@PathVariable Integer id){
        return restaurantListingService.findRestaurantById(id);
    }
}
