package com.project.tejas.restaurantlisting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantListingDTO {
    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
