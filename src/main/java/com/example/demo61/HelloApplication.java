package com.example.demo61;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tabpane.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 600);


        stage.setTitle("Online Shopping");
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        try {
            String URL = "jdbc:mysql://localhost:3306/onlineshopping";
            String USERNAME = "hay";
            String PASSWORD = "hay13.";
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            showAlert(Alert.AlertType.INFORMATION, "Database Connection", "Connected to the database successfully.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Connection Error", "Could not connect to the database.");
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
