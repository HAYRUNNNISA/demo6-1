package com.example.demo61.Controller;

import com.example.demo61.HelloApplication;
import com.example.demo61.Model.Product;
import com.example.demo61.repository.ProductRepository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import java.util.List;

import javafx.stage.Stage;

import java.sql.SQLException;

public class ProductController {
    private Stage stage;

    private ProductRepository productRepository;


    @FXML
    private Label l4;

    @FXML
    private TextField nameField;
    @FXML
    private TextField supplierField;
    @FXML
    private TextField idField;
    @FXML
    private TextField priceField;
    @FXML
    private Button Closebutton;

    @FXML
    private void initialize() {
        productRepository = new ProductRepository();
    }
    @FXML
    void cleanFields() {
        idField.setText("");
        nameField.setText("");
        supplierField.setText("");
        priceField.setText("");
        l4.setText("");

    }

    @FXML
    public void createRecord() throws Exception {
        FXMLLoader fxmlLoader  = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        String idText = idField.getText();
        String priceText=priceField.getText();
        if (idText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "ID field cannot be empty.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            String name = nameField.getText();
            String supplier = supplierField.getText();
            int price = Integer.parseInt(priceText);

            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setSupplier(supplier);
            product.setPrice(price);

            productRepository.createProduct(product);
            cleanFields();
            l4.setText("Recorded successfully :)");

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid product ID format. Please enter a valid integer ID.");
        }
    }


    @FXML
    public void fetchRecord() {
        try {
            int id = Integer.parseInt(idField.getText());
            Product product = productRepository.getProductById(id);
            if (product != null) {
                nameField.setText(product.getName());
                supplierField.setText(product.getSupplier());
                priceField.setText(String.valueOf(product.getPrice()));
            } else {
                showAlert(Alert.AlertType.WARNING, "No Record Found", "No record found with the given ID.");
                cleanFields();

            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid ID.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch record.");
            e.printStackTrace();
        }
    }
    @FXML
    public void closeConnection() {
        productRepository.closeConnection();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void closeInterface() {
        stage.close();
    }

    @FXML
    public void updateRecord() {




        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String supplier = supplierField.getText();
            int price = Integer.parseInt(priceField.getText());

            Product product = new Product(id, name, supplier,price);

            productRepository.updateProduct(product);

            cleanFields();
            l4.setText("Updated succesfully :)");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update record.");
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteRecord() {
        try {
            int id = Integer.parseInt(idField.getText());
            productRepository.deleteProduct(id);
            l4.setText("Deleted succesfully :)");
            cleanFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid ID.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete record.");
            e.printStackTrace();
        }

    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void close(ActionEvent event) {

        Stage stage = (Stage) Closebutton.getScene().getWindow();
        stage.close();
    }



    @FXML
    private void clean() {
        cleanFields();
    }
}
