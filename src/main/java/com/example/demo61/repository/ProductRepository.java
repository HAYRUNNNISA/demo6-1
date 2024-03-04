package com.example.demo61.repository;

import com.example.demo61.Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/onlineshopping";
    private static final String USERNAME = "hay";
    private static final String PASSWORD = "hay13.";
    private Connection connection=null;
    private Statement statement;

    public ProductRepository() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    public void closeConnection() {

        try {
            if (connection == null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Products (id, name, supplier, price) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getSupplier());
            statement.setInt(4, product.getPrice());
            statement.executeUpdate();
        }
    }


    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM Products WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String supplier = resultSet.getString("supplier");
                    int price =resultSet.getInt("price");
                    return new Product(id, name, supplier,price);
                }
            }
        }
        return null;
    }

    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Products SET name=?, supplier=?, price=? WHERE id=?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, product.getName());
            statement.setString(2, product.getSupplier());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getId());
            statement.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Products WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    public void fetchRecord(int id) throws  SQLException{
        String sql = "SELECT name, supplier, price FROM Products WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String supplier = resultSet.getString("supplier");
                    int price =resultSet.getInt("price");

                }
            }
        }
    }



}
