package org.velihangozek.javaecommercecli.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Long id;
    private Customer customer;
    private List<CartItem> items = new ArrayList<>();
    private Integer quantity;
    private BigDecimal totalAmount;

    public Cart() {
    }

    public Cart(Customer customer, List<CartItem> items, BigDecimal totalAmount) {
        this.customer = customer;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public Cart(Long customerId, Long productId, int quantity) {
        this.setCustomer(new Customer(customerId));
        this.setItems(List.of(new CartItem(new Product(productId))));
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
