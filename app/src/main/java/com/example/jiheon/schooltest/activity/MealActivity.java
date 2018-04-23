package com.example.jiheon.schooltest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jiheon.schooltest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealActivity extends AppCompatActivity {

    @BindView(R.id.mealView)    TextView mMealView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        // getIntent() 로 받아온 MainActivity 의 메뉴 정보를 출력
        mMealView.setText(intent.getStringExtra("menu"));
    }


}


