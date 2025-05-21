package org.velihangozek.javaecommercecli;

import org.velihangozek.javaecommercecli.exception.ExceptionMessagesConstants;
import org.velihangozek.javaecommercecli.exception.VeloStoreException;
import org.velihangozek.javaecommercecli.model.*;
import org.velihangozek.javaecommercecli.model.enums.Role;
import org.velihangozek.javaecommercecli.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class VeloStoreMain {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final CategoryService categoryService = new CategoryService();
    private static final ProductService productService = new ProductService();
    private static final CartService cartService = new CartService();
    private static User LOGGED_IN_USER;
    private static Customer LOGGED_IN_CUSTOMER;

    public static void main(String[] args) {

        while (true) {

            getMainMenu();

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        getUserMenu();
                        break;
                    case "2":
                        getCustomerMenu();
                        break;
                    case "0":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            } catch (VeloStoreException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private static void getCustomerMenu() throws VeloStoreException {
        while (true) {
            System.out.println("\n=========  CUSTOMER LOGIN PANEL =========\n");
            System.out.println("1 - Customer Register");
            System.out.println("2 - Customer Login");
            System.out.println("0 - Go back to the main menu");
            System.out.println("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerCustomer();
                    break;
                case "2":
                    loginCustomer();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void getUserMenu() throws VeloStoreException {
        while (true) {
            System.out.println("\n=========  USER LOGIN PANEL =========\n");
            System.out.println("1 - User Register");
            System.out.println("2 - User Login");
            System.out.println("0 - Go back to the main menu");
            System.out.println("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void loginUser() throws VeloStoreException {
        System.out.print("Please enter your username: ");
        String userName = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        User loggedInUser = userService.login(userName, password);

        if (loggedInUser != null && loggedInUser.getActive()) {

            LOGGED_IN_USER = loggedInUser;

            getLoggedInUserMenu();

        } else {
            throw new VeloStoreException(ExceptionMessagesConstants.USER_IS_NOT_ACTIVE);
        }
    }

    private static void getLoggedInUserMenu() throws VeloStoreException {

        while (true) {

            System.out.println("\n=========  LOGGED IN USER MENU =========\n");

            System.out.println("1 - Create a new category");
            System.out.println("2 - List all categories");
            System.out.println("3 - Delete a category by id");
            System.out.println("4 - Create a new product");
            System.out.println("5 - List all products");
            System.out.println("6 - Delete a product by id");
            System.out.println("7 - Search a product");
            System.out.println("8 - Filter products by category");
            System.out.println("9 - List all orders");

            System.out.println("0 - Go back");

            System.out.println("Please select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createCategory();
                    break;
                case "2":
                    listCategories();
                    break;
                case "3":
                    deleteCategory();
                    break;
                case "4":
                    createProduct();
                    break;
                case "5":
                    listProducts();
                    break;
                case "6":
                    deleteProduct();
                    break;
                case "7":
                    productSearch();
                    break;
                case "8":
                    productFiltering();
                    break;
                case "9":
                    orderList();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;

            }

        }
    }

    private static void productFiltering() {
        System.out.print("Enter the category name you want to filter: ");
        String categoryName = scanner.nextLine();

        List<Product> productList = productService.getAllByCategoryName(categoryName);

        System.out.print("\n========= PRODUCT FILTER RESULTS =========\n");

        productList.forEach(product ->
                System.out.printf("%s - %s - %s\n", product.getName(), product.getPrice(), product.getCategory().getName())
        );

        System.out.println("\n========= END OF PRODUCT SEARCH RESULTS =========\n");

    }

    private static void productSearch() {
        System.out.print("Enter the product name you want to search: ");
        String searchProductName = scanner.nextLine();

        List<Product> productList = productService.search(searchProductName);

        System.out.print("\n========= PRODUCT SEARCH RESULTS =========\n");

        productList.forEach(product ->
                System.out.printf("%s - %s - %s\n", product.getName(), product.getPrice(), product.getCategory().getName())
        );

        System.out.println("\n========= END OF PRODUCT SEARCH RESULTS =========\n");
    }

    private static void orderList() {
    }

    private static void deleteProduct() {
        System.out.print("Please enter the id of the product you want to delete: ");
        String productIdToBeDeleted = scanner.nextLine();
        productService.deleteById(Long.parseLong(productIdToBeDeleted));
    }

    private static void listProducts() {

        int totalPage = productService.getTotalPage();

        int page = 1;

        do {

            List<Product> productList = productService.getAll(page);

            System.out.println("\n=========  PRODUCT LIST (PAGE )" + page + "/" + totalPage + "=========\n");

            productList.forEach(product ->
                    System.out.printf("%s - %s - %s\n", product.getName(), product.getPrice(), product.getCategory().getName())
            );
            System.out.println("\n=========  END OF THE PAGE =========\n");

            System.out.println("Go to page: ");

            String pageString = scanner.nextLine();
            page = Integer.parseInt(pageString);

        } while (page <= totalPage);

        System.out.println("\n=========  END OF PRODUCT LIST =========\n");
    }

    private static void createProduct() throws VeloStoreException {
        System.out.print("Please enter the name of the product: ");
        String name = scanner.nextLine();
        System.out.print("Please enter the price of the product: ");
        String price = scanner.nextLine();
        System.out.print("Please enter the stock of the product: ");
        String stock = scanner.nextLine();
        System.out.print("Please enter the category id of the product: ");
        String categoryId = scanner.nextLine();

        Category category = categoryService.getById(Long.parseLong(categoryId));

        Product product = new Product(name, new BigDecimal(price), Integer.parseInt(stock), category);
        productService.save(product, LOGGED_IN_USER);
    }

    private static void deleteCategory() {
        System.out.print("Please enter the id of the category you want to delete: ");
        String categoryId = scanner.nextLine();

        categoryService.deleteById(Long.parseLong(categoryId));
    }

    private static void listCategories() {
        List<Category> categoryList = categoryService.getAll();

        categoryList.forEach(System.out::println);
    }

    private static void createCategory() throws VeloStoreException {
        throw new VeloStoreException("Not implemented");
    }

    private static void registerUser() throws VeloStoreException {
        System.out.print("Please enter your username: ");
        String userName = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Choose a role (ADMIN, SUPPORT) : ");
        String roleSelected = scanner.nextLine().toUpperCase();

        Role role = Role.valueOf(roleSelected);

        userService.save(userName, password, role);
    }

    private static void getMainMenu() {
        System.out.println("\n=========  SELECT LOGIN TYPE =========\n");
        System.out.println("1 - User Login (ADMIN, SUPPORT)");
        System.out.println("2 - Customer Login");
        System.out.println("0 - Exit");
        System.out.println("Please select an option: ");
    }

    private static void loginCustomer() throws VeloStoreException {
        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        LOGGED_IN_CUSTOMER = customerService.login(email, password);

        while (true) {
            System.out.println("1 - List all products");
            System.out.println("2 - Search a product");
            System.out.println("3 - Filter products by category");
            System.out.println("4 - Add a product to cart");
            System.out.println("5 - List all cart items");
            System.out.println("6 - Clear cart");
            System.out.println("7 - List all orders");

            System.out.println("0 - Go back");

            System.out.println("Please select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listProducts();
                    break;
                case "2":
                    productSearch();
                    break;
                case "3":
                    productFiltering();
                    break;
                case "4":
                    addProductToCart();
                    break;
                case "5":
                    listCart();
                    break;
                case "6":
                    clearCart();
                    break;
                case "7":
                    orderList();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }


    }

    private static void clearCart() {
    }

    private static void listCart() {
    }

    private static void addProductToCart() throws VeloStoreException {

        boolean doesContinue = true;

        while (doesContinue) {
            System.out.print("Please enter the name of the product you want to add to cart: ");
            String productName = scanner.nextLine();

            Product product = productService.getByName(productName);

            if (product == null) {
                System.out.println("Product could not be found!");
            } else {
                System.out.println("Amount of product to be added: ");
                int stock = scanner.nextInt();

                if (product.getStock() < stock) {
                    throw new VeloStoreException(ExceptionMessagesConstants.PRODUCT_STOCK_IS_NOT_VALID);
                }

                Cart cart = cartService.getByCustomerId(LOGGED_IN_CUSTOMER.getId());

                if (cart == null) {
                    cart = new Cart();
                }

                cart.getItems().add(new CartItem(product));

                System.out.println("Product added to cart successfully!");

                System.out.println("Would you like to add another product to cart? (Y/N) ");
                scanner.nextLine();
                String yesNo = scanner.nextLine();

                if (!("Y".equalsIgnoreCase(yesNo) || "YES".equalsIgnoreCase(yesNo))) {
                    doesContinue = false;
                }
            }
        }


    }


    private static void registerCustomer() throws VeloStoreException {

        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.save(name, email, password);
    }

}