package com.example.jiheon.schooltest.network.networkModel.Sleep.Response;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("sleep_out")
    private SleepOut sleepOut;

    public SleepOut getGoOut() { return sleepOut; }
    public void setGoOut(SleepOut value) { this.sleepOut = value; }
}
