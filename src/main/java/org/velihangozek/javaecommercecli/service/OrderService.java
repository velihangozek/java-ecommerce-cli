package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.OrderDAO;
import org.velihangozek.javaecommercecli.model.Customer;
import org.velihangozek.javaecommercecli.model.Order;
import org.velihangozek.javaecommercecli.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    public Order save(Customer customer, List<Product> productList) {

        BigDecimal totalAmount = productList.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setCustomer(customer);
        order.setProductList(productList);
        order.setTotalAmount(totalAmount);

        orderDAO.save(order);
        return order;
    }
}
