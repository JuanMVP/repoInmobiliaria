package com.example.inmodroid.retrofit.services;

import com.example.inmodroid.models.Photo;
import com.example.inmodroid.models.Property;
import com.example.inmodroid.models.ResponseContainer;
import com.example.inmodroid.responses.PropertiesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PropertiesService {

    @GET("/properties")
    Call<ResponseContainer<Property>> getProperties();

    @GET("/photos")
    Call<ResponseContainer<Photo>> getImagenInmueble(@Query("propertyId") String propertyId);

}
