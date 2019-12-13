package com.restervator.api;

import android.util.Log;

import com.restervator.api.core.ApiClient;
import com.restervator.api.core.ZomatoEndpoint;
import com.restervator.model.dataTransferObjects.SearchResponseDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZomatoClient {

    private static final String LOG_TAG = ZomatoClient.class.getSimpleName();
    private final ZomatoEndpoint zomatoEndpoint;
    private ApiResponseListener listener;


    public ZomatoClient() {
        zomatoEndpoint = ApiClient.getApiClient().create(ZomatoEndpoint.class);
    }

    public void search(SearchConfiguration configuration, ApiResponseListener listener) {
        Log.i(LOG_TAG, "calling search");

        Call<SearchResponseDto> call = zomatoEndpoint.search(
                configuration.getLatitude(), configuration.getLongitude(),
                configuration.getSearchKeyword(), configuration.getMaxNumberOfResults(),
                configuration.getRadiusInMeters(), configuration.getSortRestaurantsBy(),
                configuration.getSortOrder(), configuration.getCuisineIds());


        call.enqueue(new Callback<SearchResponseDto>() {
            @Override
            public void onResponse(Call<SearchResponseDto> call, Response<SearchResponseDto> response) {
                Log.d(LOG_TAG, "receiving response");
                SearchResponseDto searchResponse = response.body();

                if (searchResponse == null || !response.isSuccessful()) {
                    Log.i(LOG_TAG, "received empty response");
                    searchResponse = new SearchResponseDto();
                }

                if (listener != null) {
                    listener.onSearchResponse(searchResponse);
                }

            }

            @Override
            public void onFailure(Call<SearchResponseDto> call, Throwable t) {
                Log.e(LOG_TAG, "something went wrong: " + t.getMessage());
            }
        });
    }

}
