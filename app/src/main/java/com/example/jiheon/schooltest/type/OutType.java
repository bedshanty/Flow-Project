package com.example.jiheon.schooltest.type;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class OutType {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({OUT, SLEEP})
    public @interface Out {}

    public static final int OUT = 1;
    public static final int SLEEP = 2;
}
