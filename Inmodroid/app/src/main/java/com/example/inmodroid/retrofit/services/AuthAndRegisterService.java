package com.example.inmodroid.retrofit.services;

import com.example.inmodroid.models.Register;
import com.example.inmodroid.responses.AuthAndRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthAndRegisterService {

    @POST("/auth")
    Call<AuthAndRegisterResponse> login(@Header("Authorization") String authorization);

    @POST("/users")
    Call<AuthAndRegisterResponse> register(@Body Register registro);


}
