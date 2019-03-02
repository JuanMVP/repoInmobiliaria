package com.example.inmodroid.retrofit.services;

import com.example.inmodroid.models.AddPropertyDto;
import com.example.inmodroid.responses.AddPropertyResponse;
import com.example.inmodroid.responses.FavouriteResponse;
import com.example.inmodroid.responses.OneResponseContainer;
import com.example.inmodroid.models.Photo;
import com.example.inmodroid.models.Property;
import com.example.inmodroid.responses.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PropertiesService {

    @GET("/properties")
    Call<ResponseContainer<Property>> getProperties();

    @GET("/photos")
    Call<ResponseContainer<Photo>> getImagenInmueble(@Query("propertyId") String propertyId);

    @GET("/properties/{id}")
    Call<OneResponseContainer<Property>> getOnePropertyById(@Path("id") String id);

    @GET("/properties/mine")
    Call<ResponseContainer<Property>> getUserProperties();

    @GET("/properties/fav")
    Call<ResponseContainer<Property>> getFavouritesProperties();

    @POST("/properties/fav/{id}")
    Call<FavouriteResponse> addPropertiesFav(@Path("id") String id);

    @DELETE("/properties/fav/{id}")
    Call<FavouriteResponse> deletePropertiesFav(@Path("id") String id);

    @POST("/properties")
    Call<AddPropertyResponse> addProperty (@Body AddPropertyDto property);


}
