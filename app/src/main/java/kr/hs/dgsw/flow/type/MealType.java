package kr.hs.dgsw.flow.type;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MealType {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({BREAKFAST, LUNCH, DINNER, NEXT_BREAKFAST})
    public @interface Meal {}

    public static final int BREAKFAST = 1;
    public static final int LUNCH = 2;
    public static final int DINNER = 3;
    public static final int NEXT_BREAKFAST = 4;
}
