module com.yurov.sqlreader {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.yurov.sqlreader to javafx.fxml;
    exports com.yurov.sqlreader;
}