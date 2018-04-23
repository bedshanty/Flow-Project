package com.example.jiheon.schooltest.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NetworkService {

    @POST("auth/signin")
    Call<com.example.jiheon.schooltest.network.jsonTypes.Login.Response.Response> login(
            @Body com.example.jiheon.schooltest.network.jsonTypes.Login.Request.Request request
    );

    @POST("auth/signup")
    Call<com.example.jiheon.schooltest.network.jsonTypes.Register.Response.Response> register(
            @Body com.example.jiheon.schooltest.network.jsonTypes.Register.Request.Request request
    );

    @POST("out/go")
    Call<com.example.jiheon.schooltest.network.jsonTypes.Out.Response.Response> out(
            @Header("x-access-token") String token,
            @Body com.example.jiheon.schooltest.network.jsonTypes.Out.Request.Request request
    );

    @POST("out/sleep")
    Call<com.example.jiheon.schooltest.network.jsonTypes.Sleep.Response.Response> sleep(
            @Header("x-access-token") String token,
            @Body com.example.jiheon.schooltest.network.jsonTypes.Sleep.Request.Request request
    );
}
