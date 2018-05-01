package com.example.jiheon.schooltest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jiheon.schooltest.R;
import com.example.jiheon.schooltest.adapter.LeaveListAdapter;
import com.example.jiheon.schooltest.database.DatabaseManager;
import com.example.jiheon.schooltest.model.Leave;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaveListActivity extends AppCompatActivity {

    @BindView(R.id.leave_list_view) RecyclerView mLeaveListView;

    private LeaveListAdapter leaveListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_list);
        ButterKnife.bind(this);

        List<Leave> tempDataList = new ArrayList<>();

        for(Leave leave : DatabaseManager.selectAllLeave()) {
            tempDataList.add(leave);
        }

        leaveListAdapter = new LeaveListAdapter(tempDataList);

        mLeaveListView.setLayoutManager(new LinearLayoutManager(this));
        mLeaveListView.setAdapter(leaveListAdapter);
    }
}
