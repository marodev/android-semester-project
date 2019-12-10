package com.restervator.api;

import android.util.Log;

import com.restervator.api.core.ApiClient;
import com.restervator.api.core.ZomatoEndpoint;
import com.restervator.model.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZomatoClient {

    private static final String LOG_TAG = ZomatoClient.class.getSimpleName();
    private final ZomatoEndpoint zomatoEndpoint;
    private ApiResponseListener listener;


    public ZomatoClient(ApiResponseListener listener) {
        this.listener = listener;
        zomatoEndpoint = ApiClient.getApiClient().create(ZomatoEndpoint.class);
    }

    public void search() {
        Log.i(LOG_TAG, "calling search");
        Call<SearchResponse> call = zomatoEndpoint.search(45.464211, 9.191383, "", 1, 500, "rating", "asc", "");


        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                Log.d(LOG_TAG, "receiving response");
                SearchResponse searchResponse = response.body();

                if (searchResponse == null || !response.isSuccessful()) {
                    Log.i(LOG_TAG, "received empty response");
                    searchResponse = new SearchResponse();
                }

                if (listener != null) {
                    listener.onSearchResponse(searchResponse);
                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.e(LOG_TAG, "something went wrong: " + t.getMessage());
            }
        });
    }

}
