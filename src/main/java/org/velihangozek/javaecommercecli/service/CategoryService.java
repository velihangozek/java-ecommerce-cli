package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.CategoryDAO;
import org.velihangozek.javaecommercecli.exception.ExceptionMessagesConstants;
import org.velihangozek.javaecommercecli.exception.VeloStoreException;
import org.velihangozek.javaecommercecli.model.Category;
import org.velihangozek.javaecommercecli.model.User;
import org.velihangozek.javaecommercecli.model.enums.Role;

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
}
