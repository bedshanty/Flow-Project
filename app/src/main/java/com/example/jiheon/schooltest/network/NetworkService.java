package com.example.jiheon.schooltest.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NetworkService {

    @POST("auth/signin")
    Call<com.example.jiheon.schooltest.network.networkModel.Login.Response.Response> login(
            @Body com.example.jiheon.schooltest.network.networkModel.Login.Request.Request request
    );

    @POST("auth/signup")
    Call<com.example.jiheon.schooltest.network.networkModel.Register.Response.Response> register(
            @Body com.example.jiheon.schooltest.network.networkModel.Register.Request.Request request
    );

    @POST("out/go")
    Call<com.example.jiheon.schooltest.network.networkModel.Out.Response.Response> out(
            @Header("x-access-token") String token,
            @Body com.example.jiheon.schooltest.network.networkModel.Out.Request.Request request
    );

    @POST("out/sleep")
    Call<com.example.jiheon.schooltest.network.networkModel.Sleep.Response.Response> sleep(
            @Header("x-access-token") String token,
            @Body com.example.jiheon.schooltest.network.networkModel.Sleep.Request.Request request
    );
}
