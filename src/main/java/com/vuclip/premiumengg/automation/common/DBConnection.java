package com.vuclip.premiumengg.automation.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection dbConnection;

    private DBConnection() {
    }

    public static Connection getDbConnection() {
        if (dbConnection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String dbUrl = "jdbc:mysql://" + Configuration.dbServer + ":" + Configuration.dbPort + "/"
                        + Configuration.dbName;
                DBConnection.dbConnection = DriverManager.getConnection(dbUrl, Configuration.dbUser,
                        Configuration.dbPassword);
            } catch (Exception e) {
                System.out.println("Exception in getting DBConnection: " + e.getMessage());
            }
        }
        return dbConnection;
    }

    /**
     * @throws SQLException
     */
    public static void closeAllConnections() throws SQLException {
        if (!dbConnection.isClosed()) {
            System.out.println("==========closing Database Connection==============");
            dbConnection.close();
        }
    }
}
