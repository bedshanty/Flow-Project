package com.example.jiheon.schooltest;

import com.example.jiheon.schooltest.Activity.MainActivity;
import com.example.jiheon.schooltest.Model.DateTime;

import java.util.Calendar;

/**
 * Created by JiHeon on 2018-03-20.
 */

public class DateTimeHelper {

    // 년, 월, 일 식사종류
    private DateTime dateTime;

    private int mealType;

    // 현재 시간을 기준으로 하는 급식 파싱에 사용되는 생성자
    public DateTimeHelper() {
        // Calendar 객체를 통해 현재 년, 월, 일 계산
        Calendar cal = Calendar.getInstance();
        dateTime = new DateTime(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));

        // 시간을 이용해 Utils 계산
        setMealType(dateTime.getHour(), dateTime.getMin());
    }

    // 지정된 시간을 기준으로 하는 급식 파싱에 사용되는 생성자
    public DateTimeHelper(DateTime dateTime) {
        this.dateTime = dateTime;

        setMealType(dateTime.getHour(), dateTime.getMin());
    }

    /**
     * 파라미터로 받은 시, 분 정보로 급식 종류를 결정
     *
     * @param hour  시
     * @param min   분
     */

    public void setMealType(int hour, int min) {
        // 시 * 100 + 분 을 함으로써 시간을 정수형으로 표현
        // ex) 7:20 => 720  |  15:30 => 1530
        int sum = hour * 100 + min;

        // 사용자 학교의 아침, 점심, 저녁 시간에 따라 가져와야할 Utils 결정
        if(sum < MainActivity.BREAKFAST_TIME)
            this.mealType = Utils.BREAKFAST;

        else if(sum < MainActivity.LUNCH_TIME)
            this.mealType = Utils.LUNCH;

        else if(sum < MainActivity.DINNER_TIME)
            this.mealType = Utils.DINNER;

        else
            this.mealType = Utils.NEXT_BREAKFAST;
    }

    public int getMealType() { return mealType; }

    public DateTime getDateTime() { return dateTime; }
}