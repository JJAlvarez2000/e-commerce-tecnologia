package org.jalvarez.apiservlet.webapp.headers.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
    private static final String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
