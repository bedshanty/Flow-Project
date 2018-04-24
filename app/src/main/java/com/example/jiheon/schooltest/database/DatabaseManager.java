package com.example.jiheon.schooltest.database;

public class DatabaseManager {

    private final String CREATE_TABLE_TOKEN =
            "CREATE TABLE token (" +
                    "idx INTEGER UNIQUE," +
                    "token STRING," +
                    "PRIMARY KEY('idx')" +
                    ")";

    private final String CREATE_TABLE_MEAL =
            "CREATE TABLE meal (" +
                    "idx INTEGER," +
                    "year INTEGER," +
                    "month INTEGER," +
                    "day INTEGER," +
                    "type INTEGER," +
                    "meal STRING," +
                    "PRIMARY KEY(idx)" +
                    ")";

    private final String CREATE_TABLE_LEAVE =
            "CREATE TABLE leave (" +
                    "idx INTEGER," +
                    "start_time DATETIME," +
                    "end_time DATETIME," +
                    "reason TEXT," +
                    "type INTEGER," +
                    "PRIMARY KEY(idx)" +
                    ")";

    public String getCreateTableToken() {
        return CREATE_TABLE_TOKEN;
    }

    public String getCreateTableMeal() {
        return CREATE_TABLE_MEAL;
    }

    public String getCreateTableLeave() { return CREATE_TABLE_LEAVE; }
}
