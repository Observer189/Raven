package com.example.raven.utils;

import com.example.raven.Model.Key;
import com.example.raven.Model.Message;
import com.example.raven.ServResponse;

import java.util.ArrayList;

import javax.crypto.SecretKey;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/send")
    Call<ServResponse> sendMessage(@Query("message") String message, @Query("authorId") int id,@Query("adrId") int adrId);

    @POST("/sendKey")
    Call<ServResponse> sendKey(@Query("key") String key);

    @GET("/get")
    Call<ArrayList<Message>> getMessages(@Query("id") int id);

    @GET("/getKey")
    Call<Key> getKey(@Query("id") int id);


}
