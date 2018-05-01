package com.example.jiheon.schooltest.database.table;

import com.example.jiheon.schooltest.type.LeaveType;
import com.orm.dsl.Column;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

@Table
public class LeaveTable {
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    private String reason;
    private boolean isConfirmed;
    @LeaveType.Leave
    private int type;

    public LeaveTable() { }

    public LeaveTable(String startTime, String endTime, String reason, @LeaveType.Leave int type, boolean isConfirmed) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.type = type;
        this.isConfirmed = isConfirmed;
    }

    public String getStartTime() { return startTime; }

    public String getEndTime() { return endTime; }

    public String getReason() { return reason; }

    public boolean isConfirmed() { return isConfirmed; }

    @LeaveType.Leave
    public int getType() { return type; }
}
