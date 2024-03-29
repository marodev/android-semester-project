package com.restervator.model.domain;

public class Restaurant {

    private final String name;
    private final String fullAddress;
    private final double avgUserRating;
    private final String phoneNumber;
    private final String openingHours;
    private final String thumbnailUrl;
    private final String currency;
    private final String avgPriceForTwo;
    private final double latitude;
    private final double longitude;

    public Restaurant(String name, String fullAddress, double avgUserRating, String phoneNumber,
                      String openingHours, String thumbnailUrl, String currency,
                      String avgPriceForTwo, double latitude, double longitude) {

        this.name = name;
        this.fullAddress = fullAddress;
        this.avgUserRating = avgUserRating;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
        this.thumbnailUrl = thumbnailUrl;
        this.currency = currency;
        this.avgPriceForTwo = avgPriceForTwo;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getCurrency() {
        return currency;
    }

    public String getAvgPriceForTwo() {
        return avgPriceForTwo;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
