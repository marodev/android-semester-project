package com.restervator.api;

import com.restervator.model.dataTransferObjects.SearchResponseDto;

/**
 * interface for api callbacks
 */
public interface ApiResponseListener {
    void onSearchResponse(SearchResponseDto response);
}
