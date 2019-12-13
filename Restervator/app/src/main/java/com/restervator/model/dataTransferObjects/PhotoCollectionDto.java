package com.restervator.model.dataTransferObjects;

import com.google.gson.annotations.SerializedName;

public class PhotoCollectionDto {

    @SerializedName("photo")
    private PhotoDto photoDto;

    public PhotoCollectionDto() {
        this.photoDto = new PhotoDto();
    }

    public PhotoDto getPhotoDto() {
        return photoDto;
    }

    public void setPhotoDto(PhotoDto photoDto) {
        this.photoDto = photoDto;
    }

}
