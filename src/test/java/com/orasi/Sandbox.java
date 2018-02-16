package com.orasi;

import org.testng.annotations.Test;

import com.orasi.database.Database;
import com.orasi.database.Recordset;
import com.orasi.database.databaseImpl.MariaDBDatabase;
import com.orasi.database.databaseImpl.SQLiteDatabase;
import com.orasi.utils.TestReporter;
import com.orasi.utils.io.FileLoader;

public class Sandbox {

    @Test
    public void getAllSelectedOptions() {
        TestReporter.setDebugLevel(3);

        Database sqlLiteDb = new SQLiteDatabase(FileLoader.getAbsolutePathForResource("db/SampleDB.db"));
        Recordset rsSqlLite = new Recordset(sqlLiteDb.getResultSet("SELECT * FROM Customers"));
        rsSqlLite.print();
        for (rsSqlLite.moveFirst(); rsSqlLite.hasNext(); rsSqlLite.moveNext()) {
            System.out.println(rsSqlLite.getValue("CustomerID") + "\t" +
                    rsSqlLite.getValue("CompanyName") + "\t" +
                    rsSqlLite.getValue("ContactName") + "\t" +
                    rsSqlLite.getValue("ContactTitle") + "\t" +
                    rsSqlLite.getValue("Address") + "\t" +
                    rsSqlLite.getValue("City") + "\t" +
                    rsSqlLite.getValue("State") + "\t");
        }

        Database mariaDb = new MariaDBDatabase("localhost", "4001", "Mysql");
        mariaDb.setDbUserName("root");
        mariaDb.setDbPassword("toor");
        // Recordset rsMaria = new Recordset(mariaDb.getResultSet("SELECT * FROM EQUIPMENT"));
        // rsMaria.print();
        // mariaDb.getResultSetAsDataProvider("SELECT * FROM EQUIPMENT");
    }
}