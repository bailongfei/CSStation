package com.utils;

import java.sql.*;

public class HospDBUtil {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "****";
        String userName = "***";
        String password = "***";
        return DriverManager.getConnection(url, userName, password);
    }

}
