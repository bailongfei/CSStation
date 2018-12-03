package com.utils;

import java.sql.*;

public class HospDBUtil {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:oracle:thin:@128.100.200.86:1521:HISDB1";
        String userName = "HU_INF";
        String password = "HUINF";
        return DriverManager.getConnection(url, userName, password);
    }

}
