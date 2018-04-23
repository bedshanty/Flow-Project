package com.example.jiheon.schooltest.Database;

public class DatabaseManager {

    private final String createTableToken =
            "CREATE TABLE token (" +
                    "idx INTEGER UNIQUE," +
                    "token STRING," +
                    "PRIMARY KEY('idx')" +
                    ")";

    private final String createTableMeal =
            "CREATE TABLE meal (" +
                    "idx INTEGER," +
                    "year INTEGER," +
                    "month INTEGER," +
                    "day INTEGER," +
                    "type INTEGER," +
                    "meal STRING," +
                    "PRIMARY KEY(idx)" +
                    ")";

    public String getCreateTableToken() {
        return createTableToken;
    }

    public String getCreateTableMeal() {
        return createTableMeal;
    }
}
