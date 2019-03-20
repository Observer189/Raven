package com.example.raven;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/home")
    Call<String> getResponse(@Query("message") String message);
}
