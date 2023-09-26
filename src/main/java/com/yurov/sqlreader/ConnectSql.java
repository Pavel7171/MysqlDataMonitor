package com.yurov.sqlreader;

import java.sql.*;
/**
 * @Author Pavel Yurov
 * 22.09.2023
 */
public class ConnectSql {
    private String HOST = "";
    private final String LOGIN = "";
    private final String PASSWORD = "";
    private Connection dbConnection = null;

    public Connection getDbConnection(String base){
        String url;
        if(base==null){
            url = "jdbc:mysql://"+HOST;
        }else {
            url = "jdbc:mysql://"+HOST+"/"+base;
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, LOGIN, PASSWORD);
        }catch (Exception e){
            System.out.println("Connect is invalid"+e.getMessage());
        }
        return dbConnection;
    }
}
