package kr.hs.dgsw.flow.network.networkModel.Login.Request;

import com.google.gson.annotations.SerializedName;

public class Request {
    private String email;
    private String pw;
    @SerializedName("registration_token")
    private String registrationToken;

    public Request(String email, String pw, String registrationToken) {
        this.email = email;
        this.pw = pw;
        this.registrationToken = registrationToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getRegistrationToken() { return registrationToken; }

    public void setRegistrationToken(String registrationToken) { this.registrationToken = registrationToken; }
}
