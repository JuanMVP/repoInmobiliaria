package com.example.inmodroid.models;

public class User {

    private String email;
    private String password;
    private String name;
    private String role;
    private String picture;
    private String favs;


    public User(){}

    public User(String email, String password, String name, String role, String picture, String favs) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.picture = picture;
        this.favs = favs;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFavs() {
        return favs;
    }

    public void setFavs(String favs) {
        this.favs = favs;
    }


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", picture='" + picture + '\'' +
                ", favs='" + favs + '\'' +
                '}';
    }





}
