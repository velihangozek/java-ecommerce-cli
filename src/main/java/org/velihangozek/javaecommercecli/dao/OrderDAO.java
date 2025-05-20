package org.velihangozek.javaecommercecli.dao;

import org.velihangozek.javaecommercecli.model.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {

    private static final String INSERT_ORDER_SCRIPT = """
            INSERT INTO "/order/" (customer_id, order_date, total_amount, createddate, updateddate) 
                VALUES (?,?,?,?,?)
            """;

    public void save(Order order) {

        String url = "jdbc:postgresql://localhost:5432/velihan_store";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SCRIPT);
            preparedStatement.setLong(1, order.getCustomer().getId());
            preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(order.getOrderDate()));
            preparedStatement.setBigDecimal(3, order.getTotalAmount());
            preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(order.getCreatedDate()));
            preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(order.getUpdatedDate()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
