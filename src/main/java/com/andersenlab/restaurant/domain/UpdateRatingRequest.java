package com.andersenlab.restaurant.domain;

import lombok.Data;

@Data
public class UpdateRatingRequest {
    private String averageRating;
    private String votes;
}
