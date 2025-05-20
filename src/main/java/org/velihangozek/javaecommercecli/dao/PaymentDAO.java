package org.velihangozek.javaecommercecli.dao;

import org.velihangozek.javaecommercecli.model.Order;
import org.velihangozek.javaecommercecli.model.Payment;
import org.velihangozek.javaecommercecli.model.enums.PaymentMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {
    private static final String INSERT_PAYMENT_SCRIPT = """
            INSERT INTO payment (order_id, payment_method, amount) VALUES (?, ?, ?)
            """;

    public void save(Payment payment) {
        String url = "jdbc:postgresql://localhost:5432/velihan_store";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT_SCRIPT);
            preparedStatement.setLong(1, payment.getOrder().getId());
            preparedStatement.setString(2, payment.getPaymentMethod().name());
            preparedStatement.setBigDecimal(3, payment.getAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
