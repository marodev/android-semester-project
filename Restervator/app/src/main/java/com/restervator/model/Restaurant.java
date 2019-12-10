package com.restervator.model;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class Restaurant {

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private Location location;

    @SerializedName("cuisines")
    private String cuisines;

    @SerializedName("timings")
    private String openingHours;

    @SerializedName("average_cost_for_two")
    private String averageCostForTwo;

    @SerializedName("price_range")
    private int priceRange;

    @SerializedName("currency")
    private String currency;

    @SerializedName("thumb")
    private String thumbnailUrl;

    @SerializedName("user_rating")
    private UserRating userRating;

    @SerializedName("photos")
    private Collection<PhotoCollection> photos;

    @SerializedName("phone_numbers")
    private String phoneNumber;
    @SerializedName("establishment")
    private Collection<String> establishment;

    public Collection<String> getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Collection<String> establishment) {
        this.establishment = establishment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(String averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public Collection<PhotoCollection> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<PhotoCollection> photos) {
        this.photos = photos;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


//    public Restaurant() {
//        this.photos = new ArrayList<>();
//        this.location = new Location();
//    }
}
