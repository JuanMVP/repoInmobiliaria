package com.example.inmodroid.responses;

import com.example.inmodroid.models.Property;

import java.util.List;

public class PropertiesResponse {

    private String title;
    private String description;
    private float price;
    private int rooms;
    private float size;
    private String categoryId;
    private String address;
    private String zipcode;
    private String city;
    private String province;
    private String loc;
    private List<Property> listaPropiedades;


    public PropertiesResponse(){}


    public PropertiesResponse(String title, String description, float price, int rooms, float size, String categoryId, String address, String zipcode, String city, String province, String loc, List<Property> listaPropiedades) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.rooms = rooms;
        this.size = size;
        this.categoryId = categoryId;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.province = province;
        this.loc = loc;
        this.listaPropiedades = listaPropiedades;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
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

    public List<Property> getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(List<Property> listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }

    @Override
    public String toString() {
        return "PropertiesResponse{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", size=" + size +
                ", categoryId='" + categoryId + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", loc='" + loc + '\'' +
                ", listaPropiedades=" + listaPropiedades +
                '}';
    }
}
