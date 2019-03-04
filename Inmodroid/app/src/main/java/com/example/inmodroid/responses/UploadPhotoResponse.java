package com.example.inmodroid.responses;

public class UploadPhotoResponse {

    private String id;
    private String propertyId;
    private String imgurlink;
    private String deletehash;


    public UploadPhotoResponse(String id, String propertyId, String imgurlink, String deletehash) {
        this.id = id;
        this.propertyId = propertyId;
        this.imgurlink = imgurlink;
        this.deletehash = deletehash;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getImgurlink() {
        return imgurlink;
    }

    public void setImgurlink(String imgurlink) {
        this.imgurlink = imgurlink;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    @Override
    public String toString() {
        return "UploadPhotoResponse{" +
                "id='" + id + '\'' +
                ", propertyId='" + propertyId + '\'' +
                ", imgurlink='" + imgurlink + '\'' +
                ", deletehash='" + deletehash + '\'' +
                '}';
    }
}
