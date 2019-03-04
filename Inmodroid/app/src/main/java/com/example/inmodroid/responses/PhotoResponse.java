package com.example.inmodroid.responses;

public class PhotoResponse {

    private String id;
    private PropertiesResponse propertyId;
    private String imgurlink;
    private String deletehash;

    public PhotoResponse() {

    }


    public PhotoResponse(String id, PropertiesResponse propertyId, String imgurlink, String deletehash) {
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

    public PropertiesResponse getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(PropertiesResponse propertyId) {
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
        return "PhotoResponse{" +
                "id='" + id + '\'' +
                ", propertyId=" + propertyId +
                ", imgurlink='" + imgurlink + '\'' +
                ", deletehash='" + deletehash + '\'' +
                '}';
    }
}
