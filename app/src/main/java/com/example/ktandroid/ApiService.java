package com.example.ktandroid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://123.30.155.149:8082/api/Product/").addConverterFactory(GsonConverterFactory.create(gson)).build().create(ApiService.
                    class);


    @GET("get-product?groupName=18DTHA2-nhom2")
    Call<GetResponseDto> getAll();

    @POST("post-product?groupName=18DTHA2-nhom2")
    Call<PostResponseDto> save(@Body ProductPost product);


}
