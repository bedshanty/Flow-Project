package com.example.jiheon.schooltest.network.networkModel.Out.Response;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("go_out")
    private GoOut goOut;

    public GoOut getGoOut() { return goOut; }
    public void setGoOut(GoOut value) { this.goOut = value; }
}
