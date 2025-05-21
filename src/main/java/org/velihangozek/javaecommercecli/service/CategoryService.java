package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.CategoryDAO;
import org.velihangozek.javaecommercecli.exception.ExceptionMessagesConstants;
import org.velihangozek.javaecommercecli.exception.VeloStoreException;
import org.velihangozek.javaecommercecli.model.Category;
import org.velihangozek.javaecommercecli.model.User;
import org.velihangozek.javaecommercecli.model.enums.Role;

import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public void save(String name, User user) throws VeloStoreException {
        if (!Role.ADMIN.equals(user.getRole())) {
            throw new VeloStoreException(ExceptionMessagesConstants.USER_IS_NOT_AN_ADMIN);
        }

        categoryDAO.save(new Category(name, user, user));

        System.out.println("Category saved successfully!");
    }

    public List<Category> getAll() {
        return categoryDAO.findAll();
    }

    public void deleteById(long id) {
        categoryDAO.delete(id);
        System.out.println("Category deleted successfully!");
    }

    public Category getById(long categoryId) throws VeloStoreException {
        Category foundCategory = categoryDAO.findById(categoryId);

        if (foundCategory == null) {
            throw new VeloStoreException(ExceptionMessagesConstants.CATEGORY_NOT_FOUND);
        }

        System.out.println("Category found successfully! => " + foundCategory);

        return foundCategory;
    }
}
