package com.example.jiheon.schooltest.network.networkModel.Sleep.Response;

public class Response {
    private int status;
    private String message;
    private Data data;

    public int getStatus() { return status; }
    public void setStatus(int value) { this.status = value; }

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public Data getData() { return data; }
    public void setData(Data value) { this.data = value; }
}
