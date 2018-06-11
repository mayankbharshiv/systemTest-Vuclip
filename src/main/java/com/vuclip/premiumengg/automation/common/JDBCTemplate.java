package com.vuclip.premiumengg.automation.common;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

public class JDBCTemplate {

    private static JdbcTemplate jDBCTemplate;

    private JDBCTemplate() {
    }

    public static JdbcTemplate getDbConnection() {
        if (jDBCTemplate == null) {
            try {
                String dbUrl = "jdbc:mysql://" + Configuration.dbServer + ":" + Configuration.dbPort + "/"
                        + Configuration.dbName + "";
                System.out.println("Databse URL: " + dbUrl);
                DriverManagerDataSource ds = new DriverManagerDataSource();
                ds.setDriverClassName("com.mysql.jdbc.Driver");
                ds.setUrl(dbUrl);
                ds.setUsername(Configuration.dbUser);
                ds.setPassword(Configuration.dbPassword);
                jDBCTemplate = new JdbcTemplate(ds);
                Log4J.getLogger("JDBCTepmplate").info("Connection created to: " + dbUrl);
                ;
            } catch (Exception e) {
                System.out.println("Exception in getting DBConnection: " + e.getMessage());
            }
        }
        return jDBCTemplate;
    }

    /**
     * @throws SQLException
     */
    public static void closeAllConnections() throws SQLException {
        if (jDBCTemplate != null && jDBCTemplate.getDataSource() != null)
            if (jDBCTemplate.getDataSource().getConnection() != null)
                if (!jDBCTemplate.getDataSource().getConnection().isClosed()) {
                    System.out.println("==========closing Database Connection==============");
                    jDBCTemplate.getDataSource().getConnection().close();
                }
    }

}
