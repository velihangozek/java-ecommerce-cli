package org.velihangozek.javaecommercecli.dao;

import org.velihangozek.javaecommercecli.dao.constants.SqlScriptConstants;
import org.velihangozek.javaecommercecli.model.Cart;
import org.velihangozek.javaecommercecli.model.CartItem;
import org.velihangozek.javaecommercecli.model.Customer;
import org.velihangozek.javaecommercecli.model.Product;
import org.velihangozek.javaecommercecli.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO {

    public void clear(long customerId) {

    }

    public Cart findByCustomerId(long customerId) {

        Cart cart = null;

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.CART_FIND_BY_CUSTOMER_ID);

            preparedStatement.setLong(1, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cart = new Cart(
                        new Customer(resultSet.getLong("customer_id")),
                        List.of(new CartItem(new Product(resultSet.getLong("product_id")))),
                        BigDecimal.valueOf(123L)
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    public void save(Cart cart) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.CART_INSERT)) {

            preparedStatement.setLong(1, cart.getCustomer().getId());
            preparedStatement.setLong(2, cart.getItems().getFirst().getProduct().getId());
            preparedStatement.setInt(3, cart.getQuantity());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
