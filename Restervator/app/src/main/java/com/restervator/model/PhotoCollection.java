package com.restervator.model;

import com.google.gson.annotations.SerializedName;

public class PhotoCollection {

    @SerializedName("photo")
    private Photo photo;

    public PhotoCollection() {
        this.photo = new Photo();
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

}
