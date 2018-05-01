package com.example.jiheon.schooltest.database;

import com.example.jiheon.schooltest.database.table.LeaveTable;
import com.example.jiheon.schooltest.database.table.MealTable;
import com.example.jiheon.schooltest.database.table.TokenTable;
import com.example.jiheon.schooltest.model.Leave;
import com.example.jiheon.schooltest.type.LeaveType;
import com.orm.SugarRecord;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    public static void insertToken(String token) {
        SugarRecord.save(new TokenTable(token));
    }

    public static String selectToken() {
        return Select.from(TokenTable.class)
                .orderBy("id")
                .first()
                .getToken();
    }

    public static void insertMeal(String meal, int year, int month, int day, int mealType) {
        SugarRecord.save(new MealTable(year, month, day, mealType, meal));
    }

    public static void insertLeave(Leave leaveData) {
        SugarRecord.save(new LeaveTable(leaveData.getStartTime(), leaveData.getEndTime(),
                leaveData.getReason(), leaveData.getLeaveType(), leaveData.isConfirmed()));
    }

    public static ArrayList<Leave> selectLeave(@LeaveType.Leave final int type) {
        final List<LeaveTable> tableList =
                Select.from(LeaveTable.class)
                .where("type = " + type)
                .orderBy("start_time")
                .list();

        final ArrayList<Leave> leaveList = new ArrayList<>();

        for(LeaveTable table : tableList) {
            Leave leave = new Leave(table.getStartTime(), table.getEndTime(),
                    table.getReason(), table.getType(), table.isConfirmed());

            leaveList.add(leave);
        }

        return leaveList;
    }

    public static ArrayList<Leave> selectAllLeave() {
        final List<LeaveTable> tableList =
                Select.from(LeaveTable.class)
                        .orderBy("start_time")
                        .list();

        final ArrayList<Leave> leaveList = new ArrayList<>();

        for(LeaveTable table : tableList) {
            Leave leave = new Leave(table.getStartTime(), table.getEndTime(),
                    table.getReason(), table.getType(), table.isConfirmed());

            leaveList.add(leave);
        }

        return leaveList;
    }
}
