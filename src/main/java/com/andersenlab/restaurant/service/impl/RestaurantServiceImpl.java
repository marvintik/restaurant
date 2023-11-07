package com.andersenlab.restaurant.service.impl;

import com.andersenlab.restaurant.domain.UpdateRatingRequest;
import com.andersenlab.restaurant.exception.BadRequestException;
import com.andersenlab.restaurant.exception.ElementNotFoundException;
import com.andersenlab.restaurant.model.Restaurant;
import com.andersenlab.restaurant.repository.RestaurantRepository;
import com.andersenlab.restaurant.service.RestaurantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        if (restaurant.getId() != null) {
            throw new BadRequestException("The ID must not be provided when creating a new Restaurant");
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRating(UpdateRatingRequest updateRatingRequest, Integer id) {
        Restaurant restaurant = getById(id);
        restaurant.setAverageRating(updateRatingRequest.getAverageRating());
        restaurant.setVotes(updateRatingRequest.getVotes());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getAllByCity(String city) {
        return restaurantRepository.findByCity(city);
    }

    @Override
    public Restaurant getById(Integer id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Restaurant with id %d not found", id)));
    }

    @Override
    public void deleteById(Integer id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public List<Restaurant> sortByRating() {
        return restaurantRepository.findAll(Sort.by("averageRating").descending());
    }
}
