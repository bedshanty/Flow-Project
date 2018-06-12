package kr.hs.dgsw.flow.network.networkModel.Notice.One;

import com.google.gson.annotations.SerializedName;

import kr.hs.dgsw.flow.network.networkModel.Notice.Notice;

public class Response {
    private long status;
    private String message;
    @SerializedName("data")
    private Notice notice;

    public long getStatus() { return status; }
    public void setStatus(long value) { this.status = value; }

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public Notice getData() { return notice; }
    public void setData(Notice value) { this.notice = value; }
}
