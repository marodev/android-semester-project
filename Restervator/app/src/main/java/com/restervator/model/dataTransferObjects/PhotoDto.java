package com.restervator.model.dataTransferObjects;

import com.google.gson.annotations.SerializedName;

public class PhotoDto {

    @SerializedName("thumb_url")
    private String thumbnailUrl;

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

}
