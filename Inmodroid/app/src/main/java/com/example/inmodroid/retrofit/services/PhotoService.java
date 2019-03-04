package com.example.inmodroid.retrofit.services;

import com.example.inmodroid.responses.PhotoResponse;
import com.example.inmodroid.responses.ResponseContainer;
import com.example.inmodroid.responses.UploadPhotoResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PhotoService {

    @GET("/photos")
    Call<ResponseContainer<PhotoResponse>> getAllPhotos();

    @GET("/photos" + "/{id}")
    Call<PhotoResponse> getOnePhoto(@Path("id") String id);

    @Multipart
    @POST("/photos")
    Call<UploadPhotoResponse> uploadPhoto(@Part MultipartBody.Part photo, @Part("propertyId") RequestBody propertyId);

    @DELETE("/photos" + "/{id}")
    Call<PhotoResponse> deletePhoto(@Path("id") String id);

}
