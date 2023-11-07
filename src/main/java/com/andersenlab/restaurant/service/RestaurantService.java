package com.andersenlab.restaurant.service;

import com.andersenlab.restaurant.domain.UpdateRatingRequest;
import com.andersenlab.restaurant.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant updateRating(UpdateRatingRequest updateRatingRequest, Integer id);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getAllByCity(String city);

    Restaurant getById(Integer id);

    void deleteById(Integer id);

    List<Restaurant> sortByRating();
}
