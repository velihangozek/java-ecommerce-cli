package org.velihangozek.javaecommercecli.service;

import org.velihangozek.javaecommercecli.dao.ProductDAO;
import org.velihangozek.javaecommercecli.exception.ExceptionMessagesConstants;
import org.velihangozek.javaecommercecli.exception.VeloStoreException;
import org.velihangozek.javaecommercecli.model.Product;
import org.velihangozek.javaecommercecli.model.User;
import org.velihangozek.javaecommercecli.model.enums.Role;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void save(Product product, User user) throws VeloStoreException {

        if (!Role.ADMIN.equals(user.getRole())) {
            throw new VeloStoreException(ExceptionMessagesConstants.USER_IS_NOT_AN_ADMIN);
        }

        product.setCreatedByUser(user);
        product.setUpdatedByUser(user);

        productDAO.save(product);
        System.out.println("Product saved successfully!");

    }

    public List<Product> getAll(int page) {
        return productDAO.findAll(page);
    }

    public void deleteById(long id) {
        productDAO.delete(id);
        System.out.println("Product deleted successfully! => " + id);
    }

    public int getTotalPage() {
        return productDAO.findTotalPage();
    }

    public List<Product> search(String searchProductName) {
        return productDAO.searchByName(searchProductName);
    }

    public List<Product> getAllByCategoryName(String categoryName) {
        return productDAO.findAllByCategoryName(categoryName);
    }
}
