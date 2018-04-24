package com.example.jiheon.schooltest.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jiheon.schooltest.database.DatabaseHelper;
import com.example.jiheon.schooltest.DateTimeHelper;
import com.example.jiheon.schooltest.type.MealType;
import com.example.jiheon.schooltest.model.DateTime;
import com.example.jiheon.schooltest.network.asyncTasks.MealParser;
import com.example.jiheon.schooltest.R;
import com.example.jiheon.schooltest.Utils;

import org.hyunjun.school.School;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MealParser.MealResponse {
    MealParser mealParser;

    @BindView(R.id.timePicker)      TimePicker mTimePicker;

    DatabaseHelper myDb = new DatabaseHelper(this);

    // 사용자 학교의 정보

    public static final School.Type   SCHOOL_TYPE     = School.Type.HIGH;
    public static final School.Region SCHOOL_REGION   = School.Region.DAEGU;
    public static final String        SCHOOL_CODE     = "D100000282";

    public static final int BREAKFAST_TIME   = 720;
    public static final int LUNCH_TIME       = 1240;
    public static final int DINNER_TIME      = 1840;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            DateTimeHelper dateTimeHelper = new DateTimeHelper(
                    new DateTime(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                            mTimePicker.getHour(), mTimePicker.getMinute()));

            mealParser = new MealParser();
            mealParser.delegate = MainActivity.this;

            // MealParser AsyncTask 로 급식 정보를 받아 menu 에 저장
            mealParser.execute(myDb, year, month + 1, day, dateTimeHelper.getMealType());
        }
    };

    @OnClick(R.id.mealBtn)
    public void mealBtnClicked(View v) {
        // 현재 시간 정보를 얻기 위한 DateTimeHelper 객체 생성
        DateTimeHelper dateTimeHelper = new DateTimeHelper();

        mealParser = new MealParser();
        mealParser.delegate = MainActivity.this;

        DateTime dateTime = dateTimeHelper.getDateTime();

        int year = dateTime.getYear();
        int month = dateTime.getMonth();
        int day = dateTime.getDay();
        int type = dateTimeHelper.getMealType();

        if(Utils.mealMap.containsKey(new Pair(year, month))) {
            String meal = "";

            switch(type) {
                case MealType.BREAKFAST:
                    meal = Utils.mealMap.get(new Pair(year, month)).get(day - 1).breakfast;
                    break;

                case MealType.LUNCH:
                    meal = Utils.mealMap.get(new Pair(year, month)).get(day - 1).lunch;
                    break;

                case MealType.DINNER:
                    meal = Utils.mealMap.get(new Pair(year, month)).get(day - 1).dinner;
                    break;

                case MealType.NEXT_BREAKFAST:
                    meal = Utils.mealMap.get(new Pair(year, month)).get(day).breakfast;
                    break;
            }

            Intent intent = new Intent(MainActivity.this, MealActivity.class);
            intent.putExtra("menu", meal);

            startActivity(intent);
        } else {
            // MealParser AsyncTask 로 급식 정보를 받아 menu 에 저장
            mealParser.execute(myDb, year, month, day, type);
        }
    }

    @OnClick(R.id.outBtn)
    public void outBtnClicked(View view) {
        Intent intent = new Intent(MainActivity.this, LeaveActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.dateMealBtn)
    public void dateMealBtnClicked(View v) {
        // 현재 시간 정보를 얻기 위한 DateTimeHelper 객체 생성
        DateTime dateTime = new DateTimeHelper().getDateTime();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, dateTime.getYear(), dateTime.getMonth() - 1, dateTime.getDay());
        datePickerDialog.show();
    }

    @Override
    public void processFinish(String menu) {
        // menu 가 null 인 경우 프로그램의 에러
        if(menu == null) {
            Toast.makeText(MainActivity.this, "메뉴를 받아올 수 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            // Intent 를 생성하여 MealActivity 로 메뉴 정보를 전송한다.
            Intent intent = new Intent(MainActivity.this, MealActivity.class);
            intent.putExtra("menu", menu);

            startActivity(intent);
        }
    }
}