package org.velihangozek.javaecommercecli.exception;

public class ExceptionMessagesConstants {


    public static final String CUSTOMER_EMAIL_ALREADY_EXISTS = "Customer email already exists!";
    public static final String CUSTOMER_EMAIL_DOES_NOT_EXIST = "Customer email does not exist!";
    public static final String CUSTOMER_PASSWORD_DOES_NOT_MATCH = "Customer email or password does not match!";

    public static final String USER_EMAIL_ALREADY_EXISTS = "User email already exists!";
    public static final String USER_EMAIL_DOES_NOT_EXIST = "User email does not exist!";
    public static final String USER_PASSWORD_DOES_NOT_MATCH = "Username or password does not match!";
    public static final String USER_NAME_ALREADY_EXISTS = "Username already exists!";
    public static final String USER_IS_NOT_AN_ADMIN = "The user does not have the ADMIN role!";
    public static final String USER_IS_NOT_ACTIVE = "The user is not active or does not exist!";
    public static final String CATEGORY_NOT_FOUND = "Could not find the category!!!";
    public static final String PRODUCT_STOCK_IS_NOT_VALID = "The stock of the desired product is not valid!";

    private ExceptionMessagesConstants() {
    }
}