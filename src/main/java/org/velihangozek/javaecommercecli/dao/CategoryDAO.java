package org.velihangozek.javaecommercecli.dao;

import org.velihangozek.javaecommercecli.dao.constants.SqlScriptConstants;
import org.velihangozek.javaecommercecli.model.Category;
import org.velihangozek.javaecommercecli.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CategoryDAO implements BaseDAO<Category> {

    @Override
    public void save(Category category) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.CATEGORY_INSERT);
        ) {

            preparedStatement.setString(1, category.getName());
            preparedStatement.setLong(2, category.getCreatedByUser().getId());
            preparedStatement.setLong(3, category.getUpdatedByUser().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(long id) {

    }
}
