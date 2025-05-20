package org.velihangozek.javaecommercecli.dao;

import org.velihangozek.javaecommercecli.dao.constants.SqlScriptConstants;
import org.velihangozek.javaecommercecli.model.Customer;
import org.velihangozek.javaecommercecli.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements BaseDAO<Customer> {

    public void save(Customer customer) {

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.CUSTOMER_INSERT);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Customer findById(Long id) {

        Customer customer = null;

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.CUSTOMER_FIND_BY_ID);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setCreatedDate(new Timestamp(resultSet.getDate("createddate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(resultSet.getDate("updateddate").getTime()).toLocalDateTime());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public List<Customer> findAll() {

        List<Customer> customers = new ArrayList<>();


        try (Connection connection = DBUtil.getConnection()) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SqlScriptConstants.CUSTOMER_FIND_ALL);

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
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(long id) {

    }

    public boolean existByEmail(String email) {

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXISTS_BY_EMAIL);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer findByEmail(String email) {

        Customer customer = null;

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXISTS_BY_EMAIL);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setCreatedDate(new Timestamp(resultSet.getDate("createdDate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(resultSet.getDate("updatedDate").getTime()).toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}