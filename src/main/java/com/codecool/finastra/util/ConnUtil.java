package com.codecool.finastra.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnUtil {
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_HOST = "localhost";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "admin";
    private static final int DATABASE_PORT = 3306;
    
    public static Connection getConnection(String dbName) {
        Connection connection;
        String dbAddress = String.format("jdbc:mysql://%s:%d/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", DATABASE_HOST, DATABASE_PORT, dbName);
        try {
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection(dbAddress, DATABASE_USER, DATABASE_PASSWORD);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
