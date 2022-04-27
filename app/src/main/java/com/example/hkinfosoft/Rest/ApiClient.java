package com.example.hkinfosoft.Rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit;

    public static String BaseUrl = "https://randomuser.me/";

    public static Retrofit getRetrofitObject()
    {
        OkHttpClient client = new OkHttpClient.Builder()

                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
