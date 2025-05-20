package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.UserDAO;
import org.velihangozek.javaecommercecli.exception.ExceptionMessagesConstants;
import org.velihangozek.javaecommercecli.exception.VeloStoreException;
import org.velihangozek.javaecommercecli.model.User;
import org.velihangozek.javaecommercecli.model.enums.Role;
import org.velihangozek.javaecommercecli.util.PasswordUtil;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void save(String userName, String password, Role role) throws VeloStoreException {

        User foundUser = userDAO.findByUserName(userName);

        if (foundUser != null) {
            throw new VeloStoreException(ExceptionMessagesConstants.USER_NAME_ALREADY_EXISTS);
        }

        userDAO.save(new User(userName, PasswordUtil.hash(password), role));
        System.out.println("User saved successfully!");

    }

    public void login(String userName, String password) throws VeloStoreException {
        User foundUser = userDAO.findByUserName(userName);

        if (foundUser != null) {
            String hashedPassword = PasswordUtil.hash(password);
            boolean passwordsEqual = foundUser.getPassword().equals(hashedPassword);
            if (!passwordsEqual) {
                throw new VeloStoreException(ExceptionMessagesConstants.USER_PASSWORD_DOES_NOT_MATCH);
            }
        } else {
            throw new VeloStoreException(ExceptionMessagesConstants.USER_PASSWORD_DOES_NOT_MATCH);
        }

        System.out.println("Login successful!");
        System.out.println("Welcome, " + foundUser.getUserName() + "!");
    }
}