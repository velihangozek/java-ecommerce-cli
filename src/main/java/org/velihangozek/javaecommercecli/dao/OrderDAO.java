package org.velihangozek.javaecommercecli.dao;

import org.velihangozek.javaecommercecli.dao.constants.SqlScriptConstants;
import org.velihangozek.javaecommercecli.model.Order;
import org.velihangozek.javaecommercecli.util.DBUtil;

import java.sql.*;
import java.util.List;

public class OrderDAO implements BaseDAO<Order> {


    public void save(Order order) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.ORDER_INSERT);) {


            preparedStatement.setLong(1, order.getCustomer().getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            preparedStatement.setBigDecimal(3, order.getTotalAmount());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(order.getCreatedDate()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(order.getUpdatedDate()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(long id) {

    }
}
