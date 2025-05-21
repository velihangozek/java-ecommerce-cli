package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.CartDAO;
import org.velihangozek.javaecommercecli.model.Cart;
import org.velihangozek.javaecommercecli.model.CartItem;
import org.velihangozek.javaecommercecli.model.Customer;
import org.velihangozek.javaecommercecli.model.Product;

public class CartService {
    private CartDAO cartDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
    }

    public Cart getByCustomerId(long customerId) {
        return cartDAO.findByCustomerId(customerId);
    }

    public void addToCart(Customer loggedInCustomer, Product product, int quantity) {

        Cart cart = getByCustomerId(loggedInCustomer.getId());

        if (cart == null) {
            cart = new Cart();
        }

        cart.getItems().add(new CartItem(product));

        cartDAO.save(new Cart(loggedInCustomer.getId(), product.getId(), quantity));

        System.out.println("Product added to cart successfully!");
    }
}
