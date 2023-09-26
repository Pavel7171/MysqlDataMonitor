package com.yurov.sqlreader;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<String> baseContentView;
    @FXML
    private TableView<ObservableList<String>> dataContentView;
    @FXML
    private TableView<String> tableContentView;
    @FXML
    private TableColumn<String, String> columnNameTableView;
    @FXML
    private TableColumn<String, String> columnNameBaseView;
    @FXML
    private TableColumn<ObservableList<String>, String> columnNameDataView;


    @FXML
    void initialize() {
        GetDataFromSql connectForShowBase = new GetDataFromSql();
        connectForShowBase.getDbConnection("");
        try {
            columnNameBaseView.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
            ObservableList listBase = FXCollections.observableArrayList(connectForShowBase.getBasesList());
            baseContentView.setItems(listBase);
        } catch (Exception e) {
            System.out.println("Error... " + e.getMessage());
        }

        baseContentView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    GetDataFromSql connectForTable = new GetDataFromSql();
                    connectForTable.getDbConnection(baseContentView.getSelectionModel().getSelectedItem());
                    columnNameTableView.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
                    ObservableList listTable = FXCollections.observableArrayList(connectForTable.getTableList(baseContentView.getSelectionModel().getSelectedItem()));
                    tableContentView.setItems(listTable);
                } catch (SQLException e) {
                    System.out.println("Error... " + e.getMessage());
                }
            }
        });

        tableContentView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dataContentView.getColumns().clear();
                dataContentView.refresh();
                try {
                    GetDataFromSql connectForData = new GetDataFromSql();
                    dataContentView.setItems((connectForData.showData(baseContentView.getSelectionModel().getSelectedItem()
                            , tableContentView.getSelectionModel().getSelectedItem())));
                    for (int i = 0; i < connectForData.getDataColumnName(baseContentView.getSelectionModel().getSelectedItem()
                                    , tableContentView.getSelectionModel().getSelectedItem())
                            .size(); i++) {
                        int finalI = i;
                        columnNameDataView = new TableColumn<>(
                                connectForData.getDataColumnName(baseContentView.getSelectionModel().getSelectedItem()
                                        , tableContentView.getSelectionModel().getSelectedItem()).get(i));
                        columnNameDataView.setCellValueFactory(
                                param -> new SimpleStringProperty((String) param.getValue().get(finalI))
                        );
                        dataContentView.getColumns().add(columnNameDataView);
                    }
                } catch (Exception e) {
                    System.out.println("Error... " + e.getMessage());
                }
            }
        });
    }
}

