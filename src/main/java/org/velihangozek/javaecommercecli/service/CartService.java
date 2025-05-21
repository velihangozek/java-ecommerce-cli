package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.CartDAO;
import org.velihangozek.javaecommercecli.model.Cart;

public class CartService {
    private CartDAO cartDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
    }

    public Cart getByCustomerId(long customerId) {
        return cartDAO.findByCustomerId(customerId);
    }
}
