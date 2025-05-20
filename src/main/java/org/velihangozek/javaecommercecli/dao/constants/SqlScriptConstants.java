package org.velihangozek.javaecommercecli.dao.constants;

public class SqlScriptConstants {
    public static final String CUSTOMER_INSERT = """
                INSERT INTO customer (name, email, password) VALUES (?, ?, ?)
            """;
    public static final String CUSTOMER_FIND_BY_ID = """
                SELECT * FROM customer WHERE id = ?
            """;
    public static final String CUSTOMER_FIND_ALL = """
                SELECT * FROM customer
            """;
    public static final String CUSTOMER_EXISTS_BY_EMAIL = """
                SELECT * FROM customer WHERE email = ? LIMIT 1
            """;
    public static final String ORDER_INSERT = """
            INSERT INTO \"order\" (customer_id, order_date, total_amount) 
                VALUES (?,?,?)
            """;
    public static final String PAYMENT_INSERT = """
            INSERT INTO payment (order_id, payment_method, amount) VALUES (?, ?, ?)
            """;
    public static final String PRODUCT_SEARCH_BY_NAME = """
            SELECT * FROM product WHERE name LIKE ?
            """;

    private SqlScriptConstants() {
    }
}
