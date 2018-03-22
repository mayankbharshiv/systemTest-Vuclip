package com.vuclip.premiumengg.automation.common;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {

    private static Connection vuconnectDbConnection;

    public DBConnection() {
        setVuconnectDbConnection();
    }

    public static Connection getVuconnectDbConnection() {
        return vuconnectDbConnection;
    }

    private void setVuconnectDbConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbUrl = "jdbc:mysql://" + Configuration.dbServer + ":" + Configuration.dbPort + "/" + Configuration.vuconnectDbName;
            DBConnection.vuconnectDbConnection = DriverManager.getConnection(dbUrl, Configuration.dbUser, Configuration.dbPassword);
        } catch (Exception e) {
            System.out.println("Exception in getting DBConnection: " + e.getMessage());
        }


    }
}
