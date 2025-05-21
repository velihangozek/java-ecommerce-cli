package org.velihangozek.javaecommercecli.dao;

import org.velihangozek.javaecommercecli.dao.constants.SqlScriptConstants;
import org.velihangozek.javaecommercecli.model.Order;
import org.velihangozek.javaecommercecli.model.Payment;
import org.velihangozek.javaecommercecli.model.enums.PaymentMethod;
import org.velihangozek.javaecommercecli.util.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAO implements BaseDAO<Payment> {

    public void save(Payment payment) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.PAYMENT_INSERT);) {


            preparedStatement.setLong(1, payment.getOrder().getId());
            preparedStatement.setString(2, payment.getPaymentMethod().name());
            preparedStatement.setBigDecimal(3, payment.getAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Payment findById(Long id) {
        return null;
    }

    @Override
    public List<Payment> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public void delete(long id) {

    }
}
