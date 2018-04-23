package com.example.jiheon.schooltest.Network.jsonTypes.Out.Response;

public class GoOut {
    private int accept;
    private int idx;
    private String startTime;
    private String endTime;
    private String reason;
    private int classIdx;
    private String studentEmail;

    public int getAccept() { return accept; }
    public void setAccept(int value) { this.accept = value; }

    public int getIdx() { return idx; }
    public void setIdx(int value) { this.idx = value; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String value) { this.startTime = value; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String value) { this.endTime = value; }

    public String getReason() { return reason; }
    public void setReason(String value) { this.reason = value; }

    public int getClassIdx() { return classIdx; }
    public void setClassIdx(int value) { this.classIdx = value; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String value) { this.studentEmail = value; }
}
