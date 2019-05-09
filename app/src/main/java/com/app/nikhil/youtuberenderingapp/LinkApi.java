package com.app.nikhil.youtuberenderingapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LinkApi {


    @GET("bins/1abhdu")
    Call<LinkResponseBody> getVideos();




}




