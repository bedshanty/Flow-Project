package kr.hs.dgsw.flow.database.table;

import kr.hs.dgsw.flow.type.LeaveType;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import kr.hs.dgsw.flow.type.LeaveType;

@Table(name = "leave_table")
public class LeaveTable {
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "reason")
    private String reason;
    @Column(name = "type")
    @LeaveType.Leave
    private int type;
    @Column(name = "is_confirmed")
    private boolean isConfirmed;

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
