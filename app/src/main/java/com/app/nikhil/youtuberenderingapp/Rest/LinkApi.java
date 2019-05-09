package com.app.nikhil.youtuberenderingapp.Rest;

import com.app.nikhil.youtuberenderingapp.ApiResponseBody.LinkResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LinkApi {


    @GET("bins/ujawq")
    Call<LinkResponseBody> getVideos();


}




