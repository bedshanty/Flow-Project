package com.example.jiheon.schooltest.Network.AsyncTasks;

import android.os.AsyncTask;

import com.example.jiheon.schooltest.Utils;
import com.example.jiheon.schooltest.Network.jsonTypes.Login.Response.Response;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AsyncTask<Object, Void, Response> {

    public interface LoginResponse {
        void loginFinish(Response response);
    }

    private LoginResponse delegate;

    public Login(LoginResponse delegate) { this.delegate = delegate; }

    /**
     * @param params
     * @return Response
     */

    @Override
    protected Response doInBackground(Object... params) {
        HttpURLConnection urlConn = null;

        String urlString = Utils.makeURL("auth", "sign");

        String email = String.valueOf(params[0]);
        String pw = String.valueOf(params[1]);

        try {
            final URL url = new URL(urlString);
            urlConn = (HttpURLConnection) url.openConnection();

            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("content-type", "application/json");

            urlConn.setReadTimeout(10000);
            urlConn.setConnectTimeout(15000);
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);

            JSONObject json = new JSONObject();

            json.put("email", email);
            json.put("pw", pw);
        } catch(Exception e) {

        }

        return null;
    }
}
