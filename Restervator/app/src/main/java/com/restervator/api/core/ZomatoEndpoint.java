package com.restervator.api.core;

import com.restervator.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZomatoEndpoint {

    @GET("/api/v2.1/search")
    Call<SearchResponse> search(@Query("lat") Double latitude,
                                @Query("lon") Double longitude,
                                @Query("q") String searchKeyword,
                                @Query("count") Integer maxNumberOfResults,
                                @Query("radius") Integer radiusInMeters,
                                @Query("sort") String sortRestaurants,
                                @Query("order") String orderResultsAscOrDesc,
                                @Query("cuisines") String cuisineIds);
}
