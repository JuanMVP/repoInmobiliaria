package com.example.inmodroid.models;

import java.util.List;

public class AddPropertyDto {
    private String id;
    private String title;
    private String description;
    private double price;
    private int rooms;
    private String categoryId;
    private String address;
    private String zipcode;
    private String city;
    private String province;
    private String loc;
    private List<String> photos;




    public AddPropertyDto() {

    }

    public AddPropertyDto(String title, String description, double price, int rooms, String categoryId, String address, String zipcode, String city, String province, String loc, List<String> photos) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.categoryId = categoryId;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
        this.photos = photos;
    }

    public AddPropertyDto(String id, String title, String description, double price, int rooms, String categoryId, String address, String zipcode, String city, String province, String loc, List<String> photos) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.categoryId = categoryId;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
        this.photos = photos;
    }

    public AddPropertyDto(String title, String description, double price, int rooms, String categoryId, String address, String zipcode, String city, String province, String loc) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.categoryId = categoryId;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
    }

    public AddPropertyDto(String id, String title, String description, double price, int rooms, String categoryId, String address, String zipcode, String city, String province, String loc) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.categoryId = categoryId;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }


    @Override
    public String toString() {
        return "AddPropertyDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", categoryId='" + categoryId + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", loc='" + loc + '\'' +
                ", photos=" + photos +
                '}';
    }
}
