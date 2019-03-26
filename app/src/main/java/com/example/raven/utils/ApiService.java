package com.example.raven.utils;

import com.example.raven.ServResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/home")
    Call<ServResponse> sendMessage(@Query("message") String message, @Query("authorId") int id);
}
