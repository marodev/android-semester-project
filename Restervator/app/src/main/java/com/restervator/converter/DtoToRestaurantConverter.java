package com.restervator.converter;

import com.restervator.model.dataTransferObjects.RestaurantDto;
import com.restervator.model.dataTransferObjects.SearchResponseDto;
import com.restervator.model.domain.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class DtoToRestaurantConverter {

    // convert a single restaurant from the api to our domain model
    private static Restaurant convertToRestaurant(RestaurantDto dto) {
        return new Restaurant(dto.getName(),
                dto.getLocation().getFullAddress(),
                Double.valueOf(dto.getUserRatingDto().getAverageRating()),
                dto.getPhoneNumber(),
                dto.getOpeningHours(),
                dto.getThumbnailUrl(),
                dto.getCurrency(),
                dto.getAverageCostForTwo());
    }

    // convert the data received from the api the the actual domain model
    public static List<Restaurant> convertToRestaurantList(SearchResponseDto dto) {
        return dto.getRestaurants()
                .stream()
                .map(r -> convertToRestaurant(r.getRestaurant()))
                .collect(Collectors.toList());
    }
}
