package com.example.inmodroid.retrofit.services;

import com.example.inmodroid.models.Category;
import com.example.inmodroid.responses.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesService {

    @GET("/categories")
    Call<ResponseContainer<Category>> getCategoryList();

}
