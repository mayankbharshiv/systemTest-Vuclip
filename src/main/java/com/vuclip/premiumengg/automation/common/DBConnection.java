package com.vuclip.premiumengg.automation.common;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {

    private static Connection vuconnectDbConnection;

    private static Connection vuconnectSytemTestDbConnection;


    public DBConnection() {
        setVuconnectDbConnection();
        setVuconnectSytemTestDbConnection();
    }

    public static Connection getVuconnectDbConnection() {
        return vuconnectDbConnection;
    }

    public static void setVuconnectSytemTestDbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbUrl = "jdbc:mysql://" + Configuration.dbServer + ":" + Configuration.dbPort + "/" + Configuration.vuconnectSystemtestDbName;
            DBConnection.vuconnectSytemTestDbConnection = DriverManager.getConnection(dbUrl, Configuration.dbUser, Configuration.dbPassword);
        } catch (Exception e) {
            System.out.println("Exception in getting DBConnection: " + e.getMessage());
        }
    }

    public static Connection getVuconnectSytemTestDbConnection() {
        return vuconnectSytemTestDbConnection;
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
