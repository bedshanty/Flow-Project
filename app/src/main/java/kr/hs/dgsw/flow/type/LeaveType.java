package kr.hs.dgsw.flow.type;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LeaveType {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({OUT, SLEEP})
    public @interface Leave { }

    public static final int OUT = 1;
    public static final int SLEEP = 2;
}
