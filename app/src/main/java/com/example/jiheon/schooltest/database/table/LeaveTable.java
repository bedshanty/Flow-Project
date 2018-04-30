package com.example.jiheon.schooltest.database.table;

import com.example.jiheon.schooltest.type.LeaveType;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

@Table
public class LeaveTable {
    private String startDate;
    private String endDate;
    private String reason;
    @LeaveType.Leave
    private int type;

    public LeaveTable() { }

    public LeaveTable(String startDate, String endDate, String reason, @LeaveType.Leave int type) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.type = type;
    }

    public String getStartDate() { return startDate; }

    public String getEndDate() { return endDate; }

    public String getReason() { return reason; }

    @LeaveType.Leave
    public int getType() { return type; }
}
