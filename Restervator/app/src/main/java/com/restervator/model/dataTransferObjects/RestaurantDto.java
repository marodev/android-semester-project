package com.restervator.model.dataTransferObjects;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class RestaurantDto {

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private LocationDto location;

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
    private UserRatingDto userRatingDto;

    @SerializedName("photos")
    private Collection<PhotoCollectionDto> photos;

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

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
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

    public UserRatingDto getUserRatingDto() {
        return userRatingDto;
    }

    public void setUserRatingDto(UserRatingDto userRatingDto) {
        this.userRatingDto = userRatingDto;
    }

    public Collection<PhotoCollectionDto> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<PhotoCollectionDto> photos) {
        this.photos = photos;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


//    public RestaurantDto() {
//        this.photos = new ArrayList<>();
//        this.location = new LocationDto();
//    }
}
