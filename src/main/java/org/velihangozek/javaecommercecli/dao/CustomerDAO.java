package org.velihangozek.javaecommercecli.dao;

import org.velihangozek.javaecommercecli.model.Customer;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private final String INSERT_CUSTOMER_SCRIPT = """
                INSERT INTO customer (name, email, password) VALUES (?, ?, ?)
            """;
    private final String FIND_CUSTOMER_BY_ID_SCRIPT = """
                SELECT * FROM customer WHERE id = ?
            """;
    private final String FIND_ALL_CUSTOMERS_SCRIPT = """
                SELECT * FROM customer
            """;
    private final String CUSTOMER_EXISTS_BY_EMAIL_SCRIPT = """
                SELECT * FROM customer WHERE email = ? LIMIT 1
            """;

    public void save(Customer customer) {

        String url = "jdbc:postgresql://localhost:5432/velihan_store";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SCRIPT);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Customer findById(Long id) {
        String url = "jdbc:postgresql://localhost:5432/velihan_store";
        String user = "postgres";
        String password = "postgres";

        Customer customer = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_BY_ID_SCRIPT);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setCreatedDate(new Timestamp(resultSet.getDate("createdDate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(resultSet.getDate("updatedDate").getTime()).toLocalDateTime());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public List<Customer> findAll() {
        String url = "jdbc:postgresql://localhost:5432/velihan_store";
        String user = "postgres";
        String password = "postgres";

        List<Customer> customers = new ArrayList<>();


        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(FIND_ALL_CUSTOMERS_SCRIPT);

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setCreatedDate(new Timestamp(resultSet.getDate("createdDate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(resultSet.getDate("updatedDate").getTime()).toLocalDateTime());
                customers.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    public boolean existByEmail(String email) {
        String url = "jdbc:postgresql://localhost:5432/velihan_store";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement preparedStatement = connection.prepareStatement(CUSTOMER_EXISTS_BY_EMAIL_SCRIPT);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer findByEmail(String email) {
        String url = "jdbc:postgresql://localhost:5432/velihan_store";
        String user = "postgres";
        String password = "postgres";
        Customer customer = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            PreparedStatement preparedStatement = connection.prepareStatement(CUSTOMER_EXISTS_BY_EMAIL_SCRIPT);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            customer = new Customer();
            customer.setId(resultSet.getLong("id"));
            customer.setName(resultSet.getString("name"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPassword(resultSet.getString("password"));
            customer.setCreatedDate(new Timestamp(resultSet.getDate("createdDate").getTime()).toLocalDateTime());
            customer.setUpdatedDate(new Timestamp(resultSet.getDate("updatedDate").getTime()).toLocalDateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}