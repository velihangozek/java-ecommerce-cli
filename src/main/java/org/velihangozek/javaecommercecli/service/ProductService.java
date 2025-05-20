package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.ProductDAO;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }
}
