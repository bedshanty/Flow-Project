package com.example.jiheon.schooltest.Network.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.jiheon.schooltest.Activity.MainActivity;
import com.example.jiheon.schooltest.Activity.MealActivity;
import com.example.jiheon.schooltest.Database.DatabaseHelper;
import com.example.jiheon.schooltest.Utils;

import org.hyunjun.school.School;
import org.hyunjun.school.SchoolException;
import org.hyunjun.school.SchoolMenu;

import java.util.List;

/**
 * Created by JiHeon on 2018-03-20.
 */

public class MealParser extends AsyncTask<Object, Void, String> {

    public interface MealResponse {
        void processFinish(String output);
    }


    public MealResponse delegate = null;

    /**
     * 파라미터로 받은 날짜 정보를 통해 해당 급식을 받아온다.
     *
     * @param params 데이터베이스, 연, 월, 일, 급식 종류
     * @return 해당 급식 문자열
     */

    @Override
    protected String doInBackground(Object... params) {
        DatabaseHelper myDb = (DatabaseHelper) params[0];

        int year = (int) params[1];
        int month = (int) params[2];
        int day = (int) params[3];
        int type = (int) params[4];

        String menu = null;

        try {
            // API 기본 설정
            School api = new School(MainActivity.SCHOOL_TYPE, MainActivity.SCHOOL_REGION, MainActivity.SCHOOL_CODE);

            List<SchoolMenu> smList;
            SchoolMenu sm;

            smList = api.getMonthlyMenu(year, month);

            Utils.mealMap.put(new Pair(year, month), smList);

            int tempDay = 1;
            for(SchoolMenu tempMenu : smList) {
                myDb.insertMeal(tempMenu.breakfast, year, month, tempDay, Utils.BREAKFAST);
                myDb.insertMeal(tempMenu.lunch, year, month, tempDay, Utils.LUNCH);
                myDb.insertMeal(tempMenu.dinner, year, month, tempDay, Utils.DINNER);

                tempDay++;
            }

            // API 의 DAY 가 0 부터 시작하므로 DAY - 1
            // NEXT_BREAKFAST 인 경우 다음 날 아침을 가져와야 한다
            if(type == Utils.NEXT_BREAKFAST)
                sm = Utils.mealMap.get(new Pair(year, month)).get(day);
            else
                sm = Utils.mealMap.get(new Pair(year, month)).get(day - 1);

            // params[3], Utils 에 따라 적절한 메뉴를 받아온다
            switch(type) {
                case Utils.BREAKFAST:
                case Utils.NEXT_BREAKFAST:
                    menu = sm.breakfast;
                    break;

                case Utils.LUNCH:
                    menu = sm.lunch;
                    break;

                case Utils.DINNER:
                    menu = sm.dinner;
                    break;
            }
        } catch (SchoolException e) {
            return null;
        }
        return menu;
    }

    @Override
    protected void onPostExecute(String menu) {
        delegate.processFinish(menu);
    }
}
