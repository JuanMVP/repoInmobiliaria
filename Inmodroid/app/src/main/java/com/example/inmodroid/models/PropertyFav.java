package com.example.inmodroid.models;

import java.util.List;

public class PropertyFav {

    private String title;
    private String description;
    private String price;
    private String rooms;
    private String address;
    private String zipcode;
    private String city;
    private String province;
    private String loc;
    private OwnerId ownerId;
    private String id;
    private List<String> photos;


    public PropertyFav(String title, String description, String price, String rooms, String address, String zipcode, String city, String province, String loc, OwnerId ownerId, String id, List<String> photos) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
        this.ownerId = ownerId;
        this.id = id;
        this.photos = photos;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public OwnerId getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(OwnerId ownerId) {
        this.ownerId = ownerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }


    @Override
    public String toString() {
        return "PropertyFav{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", rooms='" + rooms + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", loc='" + loc + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", id='" + id + '\'' +
                ", photos=" + photos +
                '}';
    }
}
