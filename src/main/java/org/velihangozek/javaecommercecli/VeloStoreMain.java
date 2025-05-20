package org.velihangozek.javaecommercecli;

import org.velihangozek.javaecommercecli.model.Customer;
import org.velihangozek.javaecommercecli.service.CustomerService;

import java.util.Scanner;

public class VeloStoreMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        while (true) {

            System.out.println("\n=========  Welcome to Velo Store! =========\n");
            System.out.println("1. Customer Registration");
            System.out.println("2. Sign in");
            System.out.println("0. Exit");

            System.out.println("Please select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    saveCustomer(scanner);
                    break;
                case "2":
                    loginCustomer(scanner);
                    break;
                case "0":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }

    }

    private static void loginCustomer(Scanner scanner) {
        // TODO
    }

    private static void saveCustomer(Scanner scanner) {
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