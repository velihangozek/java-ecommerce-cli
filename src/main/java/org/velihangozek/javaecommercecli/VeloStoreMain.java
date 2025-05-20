package org.velihangozek.javaecommercecli;

import org.velihangozek.javaecommercecli.exception.VeloStoreException;
import org.velihangozek.javaecommercecli.model.enums.Role;
import org.velihangozek.javaecommercecli.service.CustomerService;
import org.velihangozek.javaecommercecli.service.UserService;

import java.util.Scanner;

public class VeloStoreMain {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();


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

        userService.login(userName, password);
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
        customerService.login(email, password);
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