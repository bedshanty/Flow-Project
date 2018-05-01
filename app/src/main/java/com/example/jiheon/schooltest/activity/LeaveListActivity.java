package com.example.jiheon.schooltest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.jiheon.schooltest.R;

import butterknife.BindView;

public class LeaveListActivity extends AppCompatActivity {

    @BindView(R.id.leave_list_view) RecyclerView mLeaveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_list);
    }
}
