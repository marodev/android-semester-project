package com.restervator.api;

import com.restervator.model.SearchResponse;

/**
 * interface for api callbacks
 */
public interface ApiResponseListener {
    void onSearchResponse(SearchResponse response);
}
