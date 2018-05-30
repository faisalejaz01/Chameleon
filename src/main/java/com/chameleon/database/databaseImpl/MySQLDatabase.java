package com.chameleon.database.databaseImpl;

import com.chameleon.database.Database;

public class MySQLDatabase extends Database {
    public MySQLDatabase(String host, String port, String dbName) {
        super.driver = "com.mysql.jdbc.Driver";
        super.connectionString = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
    }
}
