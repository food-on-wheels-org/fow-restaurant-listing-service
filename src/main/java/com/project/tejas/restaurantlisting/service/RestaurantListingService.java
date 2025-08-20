package com.project.tejas.restaurantlisting.service;

import com.project.tejas.restaurantlisting.dto.RestaurantListingDTO;
import com.project.tejas.restaurantlisting.entity.RestaurantListing;
import com.project.tejas.restaurantlisting.mapper.RestaurantListingMapper;
import com.project.tejas.restaurantlisting.repo.RestaurantListingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantListingService {
    @Autowired
    RestaurantListingRepo restaurantListingRepo;

    public List<RestaurantListingDTO> findAllRestaurants() {
        List<RestaurantListing> restaurantList = restaurantListingRepo.findAll();
        List<RestaurantListingDTO> restaurantListingDTOList = restaurantList.stream().map(restaurant -> RestaurantListingMapper.INSTANCE.mapRestauranttoRestaurantDTO(restaurant)).collect(Collectors.toList());
        return restaurantListingDTOList;
    }

    public RestaurantListingDTO addRestaurantToDb(RestaurantListingDTO restaurantListingDTO){
        RestaurantListing latestRestaurant = restaurantListingRepo.save(RestaurantListingMapper.INSTANCE.mapRestaurantDTOtoRestaurant(restaurantListingDTO));
        return RestaurantListingMapper.INSTANCE.mapRestauranttoRestaurantDTO(latestRestaurant);
    }

    public ResponseEntity<RestaurantListingDTO> findRestaurantById(Integer id){
        Optional<RestaurantListing> restaurant = restaurantListingRepo.findById(id);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(RestaurantListingMapper.INSTANCE.mapRestauranttoRestaurantDTO(restaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
