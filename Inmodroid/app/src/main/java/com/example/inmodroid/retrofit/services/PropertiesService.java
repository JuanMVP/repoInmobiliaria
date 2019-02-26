package com.example.inmodroid.retrofit.services;

import com.example.inmodroid.responses.PropertiesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PropertiesService {

    @GET("/properties")
    Call<PropertiesResponse> getProperties();

}
