package kr.hs.dgsw.flow.model;

import kr.hs.dgsw.flow.type.LeaveType;

public class Leave {
    private String startTime;
    private String endTime;
    private String reason;
    private boolean isConfirmed;

    @LeaveType.Leave
    private int leaveType;

    public Leave() {}

    public Leave(String startTime, String endTime, String reason, int leaveType, boolean isConfirmed) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.isConfirmed = isConfirmed;
        this.leaveType = leaveType;

    }

    public String getStartTime() { return startTime; }

    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }

    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getReason() { return reason; }

    public void setReason(String reason) { this.reason = reason; }

    public boolean isConfirmed() { return isConfirmed; }

    public void setConfirmed(boolean confirmed) { isConfirmed = confirmed; }

    @LeaveType.Leave
    public int getLeaveType() { return leaveType; }

    public void setLeaveType(@LeaveType.Leave int leaveType) { this.leaveType = leaveType; }
}
