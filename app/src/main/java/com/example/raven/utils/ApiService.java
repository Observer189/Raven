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
    Call<ServResponse> sendMessage(@Query("message") String message, @Query("authorId") int id,@Query("adrId") int adrId);

    @GET("/get")
    Call<ArrayList<Message>> getMessages(@Query("id") int id);
}
