package org.velihangozek.javaecommercecli.dao;

import org.velihangozek.javaecommercecli.model.Customer;
import org.velihangozek.javaecommercecli.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String SEARCH_PRODUCT_BY_NAME_SCRIPT = """
            SELECT * FROM product WHERE name LIKE ?
            """;

    public List<Product> searchByName(String name) {
        List<Product> productList = new ArrayList<>();

        String url = "jdbc:postgresql://localhost:5432/velihan_store";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_BY_NAME_SCRIPT);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setStock(resultSet.getInt("stock"));
                // TODO Category id
                product.setCreatedDate(LocalDateTime.parse(resultSet.getString("createddate")));
                product.setUpdatedDate(LocalDateTime.parse(resultSet.getString("updateddate")));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}
