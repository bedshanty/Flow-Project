package com.example.jiheon.schooltest;

import android.annotation.SuppressLint;
import android.util.Pair;

import com.example.jiheon.schooltest.Model.DateTime;

import org.hyunjun.school.SchoolMenu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JiHeon on 2018-03-26.
 */

public class Utils {
    public static final String HOST = "http://flow.cafe24app.com/";
    //public static final int BREAKFAST = 1, LUNCH = 2, DINNER = 3, NEXT_BREAKFAST = 4;
    public static final HashMap<Pair<Integer, Integer>, List<SchoolMenu>> mealMap = new HashMap<>();

    public static String urlEncoder(String text) {
        String encoded;

        try {
            encoded = URLEncoder.encode(text, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            return null;
        }

        return encoded;
    }

    public static String makeURL(String... params) {
        String url = HOST;

        for(String param : params) {
            url += "/" + param;
        }

        return url;
    }

    public static String encryptSHA512(String target) {
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-512");
            sh.update(target.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : sh.digest()) sb.append(Integer.toHexString(0xff & b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressLint("DefaultLocale")
    public static String dateFormatConverter(DateTime dateTime) {
        String dateFormat;

        dateFormat = String.valueOf(dateTime.getYear())
                + "-"
                + String.format("%02d", dateTime.getMonth())
                + "-"
                + String.format("%02d", dateTime.getDay())
                + " "
                + String.format("%02d", dateTime.getHour())
                + ":"
                + String.format("%02d", dateTime.getMin());

        return dateFormat;
    }
}
