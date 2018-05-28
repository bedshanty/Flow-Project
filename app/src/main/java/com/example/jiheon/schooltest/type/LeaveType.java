package com.example.jiheon.schooltest.type;

import android.support.annotation.IntDef;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Function;

public class LeaveType {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({OUT, SLEEP})
    public @interface Leave { }

    public static final int OUT = 1;
    public static final int SLEEP = 2;
}
