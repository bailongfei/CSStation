package com.utils;

import java.sql.*;
import java.util.Map;

/**
 * 数据库连接工具
 */
public class DBUtil {

    private static String driver = "";
    private static String url = "";
    private static String userName = "";
    private static String password = "";


    static {
        Map<String,String> dbMap = IniReader.readIniGetDBInfo();
        if (dbMap!=null){
            DBUtil.driver = dbMap.get("driver");
            DBUtil.url = dbMap.get("url");
            DBUtil.userName = dbMap.get("userName");
            DBUtil.password = dbMap.get("password");
        } else {

        }
        System.out.println("databaseInfo:");
        System.out.println("+++"+driver);
        System.out.println("+++"+url);
        System.out.println("+++"+userName);
        System.out.println("+++"+password);
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, userName, password);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                if (statement != null) {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    statement.close();
                }
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
