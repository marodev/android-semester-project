package com.restervator.api;

import android.location.Location;


public class SearchConfiguration {

    private final String sortOrder;
    private final String sortRestaurantsBy;
    private final int radiusInMeters;
    private final double latitude;
    private final double longitude;
    private final int maxNumberOfResults;
    private final String searchKeyword;
    private final String cuisineIds;

    private SearchConfiguration(String sortOrder, String sortRestaurantsBy, int radiusInMeters, double latitude, double longitude, int maxNumberOfResults, String searchKeyword, String cuisineIds) {
        this.sortOrder = sortOrder;
        this.sortRestaurantsBy = sortRestaurantsBy;
        this.radiusInMeters = radiusInMeters;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxNumberOfResults = maxNumberOfResults;
        this.searchKeyword = searchKeyword;
        this.cuisineIds = cuisineIds;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public String getSortRestaurantsBy() {
        return sortRestaurantsBy;
    }

    public int getRadiusInMeters() {
        return radiusInMeters;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getMaxNumberOfResults() {
        return maxNumberOfResults;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public String getCuisineIds() {
        return cuisineIds;
    }

    public enum SortRestaurantsBy {
        RATING,
        COST,
        REAL_DISTANCE
    }

    public enum SortOrder {
        ASC,
        DESC
    }

    public static class Builder {
        // asc
        private String sortOrder = SortOrder.ASC.name().toLowerCase();
        // read_distance
        private String sortRestaurantsBy = SortRestaurantsBy.REAL_DISTANCE.name().toLowerCase();
        // 120'000 meters
        private int radiusInMeters = 120 * 1000;
        // default location: Duomo of Milan, Italy
        private double latitude = 45.464211;
        private double longitude = 9.191383;
        private int maxNumberOfResults = 20;
        private String searchKeyword = "";
        private String cuisineIds = "";

        public Builder withSortOrder(SortOrder sortOrder) {
            this.sortOrder = sortOrder.name().toLowerCase();
            return this;
        }

        public Builder sortRestaurantsBy(SortRestaurantsBy sortRestaurantsBy) {
            this.sortRestaurantsBy = sortRestaurantsBy.name().toLowerCase();
            return this;
        }

        public Builder inRadius(int radiusInMeters) {
            this.radiusInMeters = radiusInMeters;
            return this;
        }

        public Builder nearLocation(Location location) {
            this.latitude = location.getLatitude();
            this.longitude = location.getLongitude();
            return this;
        }

        public Builder limitNumberOfResults(int maxNumberOfResults) {
            this.maxNumberOfResults = maxNumberOfResults;
            return this;
        }

        public Builder addSearchKeyword(String searchKeyword) {
            this.searchKeyword = searchKeyword;
            return this;
        }

        public SearchConfiguration build() {
            return new SearchConfiguration(sortOrder, sortRestaurantsBy, radiusInMeters, latitude, longitude, maxNumberOfResults, searchKeyword, cuisineIds);
        }

    }


}
