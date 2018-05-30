package com.chameleon.database.databaseImpl;

import com.chameleon.database.Database;

public class SQLServerDatabase extends Database {
    public SQLServerDatabase(String host, String port, String dbName) {
        super.driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
        super.connectionString = "jdbc:microsoft:sqlserver://" + host + ":" + port + ";DatabaseName=" + dbName;
    }
}
