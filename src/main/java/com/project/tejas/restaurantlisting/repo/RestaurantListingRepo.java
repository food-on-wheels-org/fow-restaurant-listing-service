package com.project.tejas.restaurantlisting.repo;

import com.project.tejas.restaurantlisting.entity.RestaurantListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantListingRepo extends JpaRepository<RestaurantListing, Integer> {
}
