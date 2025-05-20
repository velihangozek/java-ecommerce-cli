package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.PaymentDAO;
import org.velihangozek.javaecommercecli.model.Order;
import org.velihangozek.javaecommercecli.model.Payment;
import org.velihangozek.javaecommercecli.model.enums.PaymentMethod;

public class PaymentService {
    private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    public Payment save(Order order, PaymentMethod paymentMethod) {

        Payment payment = new Payment(order, paymentMethod);
        paymentDAO.save(payment);
        return payment;

    }
}
