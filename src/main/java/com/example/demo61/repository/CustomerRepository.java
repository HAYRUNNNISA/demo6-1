package com.example.demo61.repository;

import com.example.demo61.Model.Customer;


import java.sql.*;

public class CustomerRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/onlineshopping";
    private static final String USERNAME = "hay";
    private static final String PASSWORD = "hay13.";
    private Connection connection;
    private Statement statement ;
    private ResultSet result;

    public CustomerRepository() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveCustomer(Customer c) throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();

        String query = "INSERT INTO Customers VALUES (" + c.getId() +",'" + c.getName() + "','" + c.getAddress() +"','" + c.getTelephone() +"');";
        statement.executeUpdate(query);
    }

    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customers SET name=?, address=?, telephone=? WHERE id=?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getTelephone());
            statement.setInt(4, customer.getId());
            statement.executeUpdate();
        }
    }
    public Customer getCustomerById(int id) throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        String query = "SELECT * FROM Customers WHERE id =" + id +";";
        statement = connection.createStatement();
        result=statement.executeQuery(query);
        Customer customer=new Customer();
        if (result.next())
        {
            customer.setId(result.getInt(1));
            customer.setName(result.getString(2));
            customer.setAddress(result.getString(3));
            customer.setTelephone(result.getString(4));

        }


        connection.close();
         return customer;

    }
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Customers WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }





}
