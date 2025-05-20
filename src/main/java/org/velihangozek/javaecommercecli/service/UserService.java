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

    public void save(String userName, String password) throws VeloStoreException {
        User foundUser = userDAO.findByUserName(userName);

        if (foundUser != null) {
            throw new VeloStoreException(ExceptionMessagesConstants.USER_NAME_ALREADY_EXISTS);
        }

        userDAO.save(new User(userName, PasswordUtil.hash(password), Role.SUPPORT));

    }

    public User login(String userName, String password) throws VeloStoreException {
        User foundUser = userDAO.findByUserName(userName);

        if (foundUser != null) {
            String hashedPassword = PasswordUtil.hash(password);
            boolean passwordsEqual = foundUser.getPassword().equals(hashedPassword);
            if (passwordsEqual) {
                System.out.println("User login successful!");
            } else {
                throw new VeloStoreException(ExceptionMessagesConstants.USER_PASSWORD_DOES_NOT_MATCH);
            }
        }
        return foundUser;
    }

}
