package com.example.phongtro.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/phongtro?useSSL=false"  ;
    private static final String USER = "root";
    private static final String PASSWORD = "Thanhlinh22";



    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connect database successfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }
        return conn;
    }
}
