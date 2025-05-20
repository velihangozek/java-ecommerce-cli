package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.CustomerDAO;
import org.velihangozek.javaecommercecli.model.Customer;
import org.velihangozek.javaecommercecli.util.PasswordUtil;

public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public void save(String name, String email, String password) {

        Customer customer = new Customer(name, email, PasswordUtil.hash(password));

        customerDAO.save(customer);

        System.out.println("Customer saved successfully!");
    }
}
