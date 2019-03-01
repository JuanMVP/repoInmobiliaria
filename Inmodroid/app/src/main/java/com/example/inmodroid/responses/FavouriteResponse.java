package com.example.inmodroid.responses;

import java.util.List;
import java.util.Objects;

public class FavouriteResponse {

    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private List<String> favs;
    private List<String> keywords;
    private String picture;

    public FavouriteResponse(String id, String name, String email, String password, String role, List<String> favs, List<String> keywords, String picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.favs = favs;
        this.keywords = keywords;
        this.picture = picture;
    }


    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getFavs() {
        return favs;
    }

    public void setFavs(List<String> favs) {
        this.favs = favs;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    @Override
    public String toString() {
        return "FavouriteResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", favs=" + favs +
                ", keywords=" + keywords +
                ", picture='" + picture + '\'' +
                '}';
    }
}
