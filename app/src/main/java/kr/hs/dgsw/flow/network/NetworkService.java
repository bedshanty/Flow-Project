package kr.hs.dgsw.flow.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NetworkService {

    @POST("auth/signin")
    Call<kr.hs.dgsw.flow.network.networkModel.Login.Response.Response> login(
            @Body kr.hs.dgsw.flow.network.networkModel.Login.Request.Request request
    );

    @POST("auth/signup")
    Call<kr.hs.dgsw.flow.network.networkModel.Register.Response.Response> register(
            @Body kr.hs.dgsw.flow.network.networkModel.Register.Request.Request request
    );

    @POST("out/go")
    Call<kr.hs.dgsw.flow.network.networkModel.Out.Response.Response> out(
            @Header("x-access-token") String token,
            @Body kr.hs.dgsw.flow.network.networkModel.Out.Request.Request request
    );

    @POST("out/sleep")
    Call<kr.hs.dgsw.flow.network.networkModel.Sleep.Response.Response> sleep(
            @Header("x-access-token") String token,
            @Body kr.hs.dgsw.flow.network.networkModel.Sleep.Request.Request request
    );
}
