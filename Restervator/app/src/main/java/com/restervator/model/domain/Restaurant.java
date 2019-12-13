package com.restervator.model.domain;

public class Restaurant {

    private final String name;
    private final String fullAddress;
    private final double avgUserRating;
    private final String phoneNumber;
    private final String openingHours;
    private final String thumbnailUrl;

    public Restaurant(String name, String fullAddress, double avgUserRating, String phoneNumber, String openingHours, String thumbnailUrl) {

        this.name = name;
        this.fullAddress = fullAddress;
        this.avgUserRating = avgUserRating;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public double getAvgUserRating() {
        return avgUserRating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
