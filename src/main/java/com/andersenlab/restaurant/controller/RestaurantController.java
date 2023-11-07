package com.andersenlab.restaurant.controller;

import com.andersenlab.restaurant.domain.UpdateRatingRequest;
import com.andersenlab.restaurant.model.Restaurant;
import com.andersenlab.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return restaurantService.create(restaurant);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant update(@RequestBody UpdateRatingRequest updateRatingRequest, @PathVariable Integer id) {
        return restaurantService.updateRating(updateRatingRequest, id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAll() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping(value = "/query", params = "city")
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getByCity(@RequestParam String city) {
        return restaurantService.getAllByCity(city);
    }

    @GetMapping(value = "/query", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant getById(@RequestParam Integer id) {
        return restaurantService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        restaurantService.deleteById(id);
    }

    @GetMapping("/sort")
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> sort() {
        return restaurantService.sortByRating();
    }


}
