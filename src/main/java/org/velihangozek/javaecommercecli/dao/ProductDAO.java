package org.velihangozek.javaecommercecli.dao;

import org.velihangozek.javaecommercecli.dao.constants.SqlScriptConstants;
import org.velihangozek.javaecommercecli.model.Customer;
import org.velihangozek.javaecommercecli.model.Product;
import org.velihangozek.javaecommercecli.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements BaseDAO<Product> {

    public List<Product> searchByName(String name) {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.PRODUCT_SEARCH_BY_NAME);) {

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

    @Override
    public void save(Product product) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.PRODUCT_INSERT);) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setLong(4, product.getCategory().getId());
            preparedStatement.setLong(5, product.getCreatedByUser().getId());
            preparedStatement.setLong(6, product.getUpdatedByUser().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(long id) {

    }
}
