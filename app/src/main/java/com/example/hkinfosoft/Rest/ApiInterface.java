package com.example.hkinfosoft.Rest;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {


    /*=======================================================User list Api Start=======================================================*/

    @GET("api")
    Call<JsonObject> getUserList();

    @GET("api")
    Call<JsonObject> getUserList(@Query("results") int results,@Query("page") int page);

    /*=======================================================User list Api Over=======================================================*/

}

