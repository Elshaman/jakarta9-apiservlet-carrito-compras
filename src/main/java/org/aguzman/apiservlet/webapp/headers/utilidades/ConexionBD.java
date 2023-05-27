package org.aguzman.apiservlet.webapp.headers.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static String url  = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=America/Santiago";
    private static String userName = "root";
    private static String password = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,userName , password);
    }
}
