package com.example.jiheon.schooltest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + "token");
        db.execSQL("DROP TABLE IF EXISTS " + "meal");
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

    public void insertOut(String startTime, String endTime, int outType) {
        new Thread(() -> {
            final SQLiteDatabase db = this.getWritableDatabase();

            String sql = "INSERT OR REPLACE INTO out(start_time, end_time, type) VALUES('";
            sql += startTime + "', '";
            sql += endTime + "', '";
            sql += outType + "')";

            db.execSQL(sql);
        }).run();
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
