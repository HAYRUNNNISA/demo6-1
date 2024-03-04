module com.example.demo61 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo61 to javafx.fxml;
    exports com.example.demo61;
    opens com.example.demo61.Controller to javafx.fxml;
}