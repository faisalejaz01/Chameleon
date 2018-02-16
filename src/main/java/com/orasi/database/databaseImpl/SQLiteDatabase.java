package com.orasi.database.databaseImpl;

import com.orasi.database.Database;

public class SQLiteDatabase extends Database {
    public SQLiteDatabase(String filepath) {
        super.driver = null;
        super.connectionString = "jdbc:sqlite:C:/Users/Justin/git/Selenium-Java-Core/db/SampleDB.db";
        super.isTypeForwardOnly = true;
    }
}
