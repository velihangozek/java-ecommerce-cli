package org.velihangozek.javaecommercecli.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private DBUtil() {
    }

    private static String URL = "jdbc:postgresql://localhost:5432/velihan_store";
    private static String user = "postgres";
    private static String password = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
    }
}
