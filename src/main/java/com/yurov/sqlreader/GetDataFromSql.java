package com.yurov.sqlreader;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @Author Pavel Yurov
 * 22.09.2023
 */
public class GetDataFromSql extends ConnectSql {
    public ArrayList<String> getBasesList() throws SQLException {
        ArrayList<String> baseList = new ArrayList<>();
        String sql = "SHOW DATABASES";
        Statement statement = getDbConnection(null).createStatement();
        statement.executeQuery(sql);
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            baseList.add(resultSet.getString(1));
        }
        return baseList;
    }

    public ArrayList<String> getTableList(String base) throws SQLException {
        ArrayList<String> tableList = new ArrayList<>();
        String sql = "SHOW TABLES";
        Statement statement = getDbConnection(base).createStatement();
        statement.executeQuery(sql);
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            tableList.add(resultSet.getString(1));
        }
        return tableList;
    }

    public ArrayList<String> getDataColumnName(String base, String table) {
        ArrayList<String> columnNameList = new ArrayList<>();
        String sql = "SELECT * FROM " + table;
        try {
            Statement statement = getDbConnection(base).createStatement();
            statement.executeQuery(sql);
            ResultSet resultSet = statement.getResultSet();
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNameList.add(resultSet.getMetaData().getColumnName(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return columnNameList;
    }

    public ObservableList<ObservableList<String>> showData(String base, String table) {
        ObservableList<ObservableList<String>> data;
        ObservableList<String> row;
        data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM " + table;
        try {
            Statement statement = getDbConnection(base).createStatement();
            statement.executeQuery(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
