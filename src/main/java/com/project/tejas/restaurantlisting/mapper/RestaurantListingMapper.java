package com.project.tejas.restaurantlisting.mapper;

import com.project.tejas.restaurantlisting.dto.RestaurantListingDTO;
import com.project.tejas.restaurantlisting.entity.RestaurantListing;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantListingMapper {
    RestaurantListingMapper INSTANCE = Mappers.getMapper(RestaurantListingMapper.class);

    RestaurantListing mapRestaurantDTOtoRestaurant(RestaurantListingDTO restaurantDTO);
    RestaurantListingDTO mapRestauranttoRestaurantDTO(RestaurantListing restaurant);

}
