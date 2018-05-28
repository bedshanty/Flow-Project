package kr.hs.dgsw.flow;

import kr.hs.dgsw.flow.network.NetworkService;

import kr.hs.dgsw.flow.network.NetworkService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private Retrofit retrofit;
    private NetworkService service;

    public RetrofitBuilder() {
        retrofit = new Retrofit.Builder().baseUrl(Utils.HOST)
                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(NetworkService.class);
    }

    public NetworkService getService() {
        return service;
    }
}
