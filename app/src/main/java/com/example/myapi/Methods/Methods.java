package com.example.myapi.Methods;

import com.example.myapi.Models.Model;
import com.example.myapi.Models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Methods {

    @GET("api/users?page=2")
    Call<Model> getAllData();


    @FormUrlEncoded
    @POST("/api/users")
    Call<User> getUserInformation(@Field("name") String name, @Field("job") String job);

    @PUT("/api/users/2")
    Call<User.User2.Data> putinfo(@Field("id") String id,User.User2.Data data);
}
