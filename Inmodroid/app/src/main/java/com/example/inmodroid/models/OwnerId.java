package com.example.inmodroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnerId {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public OwnerId() {
    }

    /**
     *
     * @param picture
     * @param id
     * @param name
     */
    public OwnerId(String id, String picture, String name) {
        super();
        this.id = id;
        this.picture = picture;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
