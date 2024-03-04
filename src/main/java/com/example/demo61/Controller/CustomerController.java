package com.example.demo61.Controller;


import com.example.demo61.Model.Customer;

import com.example.demo61.repository.CustomerRepository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.stage.Stage;

import java.sql.SQLException;
public class CustomerController {

    private CustomerRepository customerrepository;
    @FXML
    private Label u;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField telField;
    @FXML
    private Button Closebutton;

    @FXML
    private void initialize() {customerrepository = new CustomerRepository();
    }
    @FXML
    public void clear() throws SQLException{
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        telField.setText("");
        u.setText("");
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    public void create() {
        String idText = idField.getText();
        if (idText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "ID field cannot be empty.");
            return;
        }
        try {
            CustomerRepository customerRepository = new CustomerRepository();
            Customer c = new Customer();
            c.setId(Integer.parseInt(idField.getText()));
            c.setName(nameField.getText());
            c.setAddress(addressField.getText());
            c.setTelephone(telField.getText());
            customerRepository.saveCustomer(c);

            clear();
            u.setText("Recorded successfully :)");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid customer ID format. Please enter a valid integer ID.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to create record.");
            e.printStackTrace();
        }
    }

    @FXML
    public void updateCustomers() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String address = addressField.getText();
            String telephone = telField.getText();
            Customer customer = new Customer(id, name, address, telephone);
            customerrepository.updateCustomer(customer);
            u.setText("Updated succesfully :)");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid customer ID format. Please enter a valid integer ID.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update record.");
            e.printStackTrace();
        }
    }

    @FXML
    public void fetch() {
        String idText = idField.getText();

        try {
            CustomerRepository customerRepository = new CustomerRepository();
            Customer customer = customerRepository.getCustomerById(Integer.parseInt(idField.getText()));
            if (customer == null || customer.getId() == 0) {
                showAlert(Alert.AlertType.INFORMATION, "No such record!", "No customer found with this ID");
                idField.setText("");
                idField.requestFocus();
                clear();
                return;
            }

            nameField.setText(customer.getName());
            addressField.setText(customer.getAddress());
            telField.setText(customer.getTelephone());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid customer ID format. Please enter a valid integer ID.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch record.");
            e.printStackTrace();
        }
    }
    @FXML
    void close(ActionEvent event) {

        Stage stage = (Stage) Closebutton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void deleteCustomer() {
        try {
            int id = Integer.parseInt(idField.getText());
            customerrepository.deleteProduct(id);
            u.setText("Deleted succesfully :)");
            clear();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid ID.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete record.");
            e.printStackTrace();
        }

    }

}