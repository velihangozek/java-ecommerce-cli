package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.CustomerDAO;
import org.velihangozek.javaecommercecli.exception.ExceptionMessagesConstants;
import org.velihangozek.javaecommercecli.exception.VeloStoreException;
import org.velihangozek.javaecommercecli.model.Customer;
import org.velihangozek.javaecommercecli.util.PasswordUtil;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    public void save(String name, String email, String password) throws VeloStoreException {

        boolean customerExists = customerDAO.existByEmail(email);

        if (customerExists) {
            throw new VeloStoreException(ExceptionMessagesConstants.CUSTOMER_EMAIL_ALREADY_EXISTS);
        }

        Customer customer = new Customer(name, email, PasswordUtil.hash(password));

        customerDAO.save(customer);

        System.out.println("Customer saved successfully!");
    }

    public void login(String email, String password) throws VeloStoreException {
        boolean customerExists = customerDAO.existByEmail(email);

        if (!customerExists) {
            throw new VeloStoreException(ExceptionMessagesConstants.CUSTOMER_EMAIL_DOES_NOT_EXIST);
        }

        String hashedPassword = PasswordUtil.hash(password);

        Customer foundCustomer = customerDAO.findByEmail(email);

        if (foundCustomer != null) {
            boolean passwordsEqual = foundCustomer.getPassword().equals(hashedPassword);
            if (passwordsEqual) {
                System.out.println("Customer login successful!");
            } else {
                throw new VeloStoreException(ExceptionMessagesConstants.CUSTOMER_PASSWORD_DOES_NOT_MATCH);
            }
        }
    }
}
