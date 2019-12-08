package com.restervator.model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("thumb_url")
    private String thumbnailUrl;

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

}
