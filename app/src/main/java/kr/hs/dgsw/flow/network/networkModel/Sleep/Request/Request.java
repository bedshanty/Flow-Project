package kr.hs.dgsw.flow.network.networkModel.Sleep.Request;

import com.google.gson.annotations.SerializedName;

public class Request {
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("end_time")
    private String endTime;
    private String reason;

    public Request(String startTime, String endTime, String reason) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
    }

    public String getStartTime() { return startTime; }

    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }

    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getReason() { return reason; }

    public void setReason(String reason) { this.reason = reason; }
}
