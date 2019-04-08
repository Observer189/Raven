package com.example.raven.utils;

import com.example.raven.Model.Message;
import com.example.raven.ServResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/send")
    Call<ServResponse> sendMessage(@Query("message") String message,
                                   @Query("authorId") int id,
                                   @Query("adrId") int adrId,
                                   @Query("key") String key);

    @GET("/get")
    Call<ArrayList<Message>> getMessages(@Query("id") int id);

    @POST("/register")
    Call<Integer> register(@Query("id") int id);
}
