package com.orasi.database.databaseImpl;

import com.orasi.database.Database;

public class SQLiteDatabase extends Database {
    public SQLiteDatabase(String filepath) {
        super.driver = null;
        super.connectionString = "jdbc:sqlite:" + filepath;
        super.isTypeForwardOnly = true;
    }
}
