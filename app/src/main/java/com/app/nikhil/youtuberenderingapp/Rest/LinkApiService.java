package com.app.nikhil.youtuberenderingapp.Rest;

import com.app.nikhil.youtuberenderingapp.ApiResponseBody.LinkResponseBody;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LinkApiService {

    LinkApi linkApi;

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public LinkApiService() {
        linkApi=LinkApiService.getClient().create(LinkApi.class);
    }


    public void getVideoItems(final ResponseCallback<LinkResponseBody> callback)
    {
        Call<LinkResponseBody> call=linkApi.getVideos();

        call.enqueue(new Callback<LinkResponseBody>() {

            @Override
            public void onResponse(Call<LinkResponseBody> call, Response<LinkResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.success(response.body());
                }

            }

            @Override
            public void onFailure(Call<LinkResponseBody> call, Throwable t) {

            }
    });
}


}
