module com.example.cw0301 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.cw0301 to javafx.fxml;
    exports com.example.cw0301;
    exports com.example.cw0301.model;
    opens com.example.cw0301.model to javafx.fxml;
    exports com.example.cw0301.controller;
    opens com.example.cw0301.controller to javafx.fxml;
    exports com.example.cw0301.data;
    opens com.example.cw0301.data to javafx.fxml;
}