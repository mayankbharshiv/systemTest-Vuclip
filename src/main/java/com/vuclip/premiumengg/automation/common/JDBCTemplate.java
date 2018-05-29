package com.vuclip.premiumengg.automation.common;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

public class JDBCTemplate {

    private static JdbcTemplate jDBCTemplate;

    public JDBCTemplate() {
        setDbConnection();
    }

    public static JdbcTemplate getDbConnection() {
        return jDBCTemplate;
    }

    /**
     * @throws SQLException
     */
    public static void closeAllConnections() throws SQLException {
        if (!jDBCTemplate.getDataSource().getConnection().isClosed()) {
            System.out.println("==========closing Database Connection==============");
            jDBCTemplate.getDataSource().getConnection().close();
        }
    }

    private void setDbConnection() {
        try {
            String dbUrl = "jdbc:mysql://" + Configuration.dbServer + ":" + Configuration.dbPort + "/"
                    + Configuration.dbName + "?useSSL=false";
            System.out.println(dbUrl);
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl(dbUrl);
            ds.setUsername(Configuration.dbUser);
            ds.setPassword(Configuration.dbPassword);
            jDBCTemplate = new JdbcTemplate(ds);
        } catch (Exception e) {
            System.out.println("Exception in getting DBConnection: " + e.getMessage());
        }
    }

}
