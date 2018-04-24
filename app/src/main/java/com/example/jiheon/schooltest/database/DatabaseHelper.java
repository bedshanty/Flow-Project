package com.example.jiheon.schooltest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jiheon.schooltest.model.Leave;
import com.example.jiheon.schooltest.type.LeaveType;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final DatabaseManager dbManager = new DatabaseManager();

    private static final String DB_NAME = "ahnt_database.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbManager.getCreateTableToken());
        db.execSQL(dbManager.getCreateTableMeal());
        db.execSQL(dbManager.getCreateTableLeave());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + "token");
        db.execSQL("DROP TABLE IF EXISTS " + "meal");
        db.execSQL("DROP TABLE IF EXISTS " + "leave");
    }

    public void insertToken(String token) {
        new Thread(() -> {
            final SQLiteDatabase db = this.getWritableDatabase();
            final ContentValues contentValues = new ContentValues();
            contentValues.put("token", token);
            db.insert("token", null, contentValues);
        }).run();
    }

    public void insertMeal(String meal, int year, int month, int day, int mealType) {
        new Thread(() -> {
            final SQLiteDatabase db = this.getWritableDatabase();

            String sql = "INSERT OR REPLACE INTO meal(year, month, day, type, meal) VALUES('";
            sql += year + "', '";
            sql += month + "', '";
            sql += day + "', '";
            sql += mealType + "', '";
            sql += meal + "')";

            db.execSQL(sql);
        }).run();
    }

    public void insertLeave(Leave outData) {
        new Thread(() -> {
            final SQLiteDatabase db = this.getWritableDatabase();

            String sql = "INSERT OR REPLACE INTO leave(start_time, end_time, reason, type) VALUES('";
            sql += outData.getStartTime() + "', '";
            sql += outData.getEndTime() + "', '";
            sql += outData.getReason() + "', '";
            sql += outData.getLeaveType() + "')";

            db.execSQL(sql);
        }).run();
    }

    public ArrayList<Leave> getOut() {
        final ArrayList<Leave> outData = new ArrayList<>();
        final SQLiteDatabase db = this.getWritableDatabase();
        final Cursor res = db.rawQuery(
                "SELECT * FROM leave WHERE type = " + LeaveType.OUT
                        + " ORDER BY start_time", null);

        while(res.moveToNext()) {
            final Leave leaveTemp = new Leave();
            leaveTemp.setStartTime(res.getString(res.getColumnIndex("start_time")));
            leaveTemp.setEndTime(res.getString(res.getColumnIndex("end_time")));
            leaveTemp.setReason(res.getString(res.getColumnIndex("reason")));
            leaveTemp.setLeaveType(LeaveType.OUT);

            outData.add(leaveTemp);
        }

        return outData;
    }

    public ArrayList<Leave> getSleep() {
        final ArrayList<Leave> sleepData = new ArrayList<>();
        final SQLiteDatabase db = this.getWritableDatabase();
        final Cursor res = db.rawQuery(
                "SELECT * FROM leave WHERE type = " + LeaveType.SLEEP
                        + " ORDER BY start_time", null);

        while(res.moveToNext()) {
            final Leave leaveTemp = new Leave();
            leaveTemp.setStartTime(res.getString(res.getColumnIndex("start_time")));
            leaveTemp.setEndTime(res.getString(res.getColumnIndex("end_time")));
            leaveTemp.setReason(res.getString(res.getColumnIndex("reason")));
            leaveTemp.setLeaveType(LeaveType.SLEEP);

            sleepData.add(leaveTemp);
        }

        return sleepData;
    }

    public ArrayList<Leave> getAllLeave() {
        final ArrayList<Leave> leaveData = new ArrayList<>();
        final SQLiteDatabase db = this.getWritableDatabase();
        final Cursor res = db.rawQuery(
                "SELECT * FROM leave ORDER BY start_time", null);

        while(res.moveToNext()) {
            final Leave leaveTemp = new Leave();
            leaveTemp.setStartTime(res.getString(res.getColumnIndex("start_time")));
            leaveTemp.setEndTime(res.getString(res.getColumnIndex("end_time")));
            leaveTemp.setReason(res.getString(res.getColumnIndex("reason")));
            leaveTemp.setLeaveType(res.getInt(res.getColumnIndex("type")));

            leaveData.add(leaveTemp);
        }

        return leaveData;
    }

    public String getToken() {
        final SQLiteDatabase db = this.getWritableDatabase();
        final Cursor res = db.rawQuery(
                "SELECT token FROM token ORDER BY idx DESC LIMIT 1", null);

        final StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append(res.getString(0));
        }

        String token = buffer.toString();
        res.close();

        return token;
    }
}
