package com.example.inmodroid.models;

public class Photo {


    public String id;
    public String propertyId;
    public String imgurLink;
    public String deletehash;


    public Photo(String id, String propertyId, String imgurLink, String deletehash) {
        this.id = id;
        this.propertyId = propertyId;
        this.imgurLink = imgurLink;
        this.deletehash = deletehash;
    }

    public Photo() {
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

    public String getImgurLink() {
        return imgurLink;
    }

    public void setImgurLink(String imgurLink) {
        this.imgurLink = imgurLink;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public void setDeletehash(String deletehash) {
        this.deletehash = deletehash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (id != null ? !id.equals(photo.id) : photo.id != null) return false;
        if (propertyId != null ? !propertyId.equals(photo.propertyId) : photo.propertyId != null)
            return false;
        if (imgurLink != null ? !imgurLink.equals(photo.imgurLink) : photo.imgurLink != null)
            return false;
        return deletehash != null ? deletehash.equals(photo.deletehash) : photo.deletehash == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (propertyId != null ? propertyId.hashCode() : 0);
        result = 31 * result + (imgurLink != null ? imgurLink.hashCode() : 0);
        result = 31 * result + (deletehash != null ? deletehash.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", propertyId='" + propertyId + '\'' +
                ", imgurLink='" + imgurLink + '\'' +
                ", deletehash='" + deletehash + '\'' +
                '}';
    }


}
