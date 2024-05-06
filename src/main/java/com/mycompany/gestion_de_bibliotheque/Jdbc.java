/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author asma
 */
public class Jdbc {
    
    

    private static final String URL = "jdbc:mysql://localhost:3306/bd_gb";
    private static final String USER = "Admin";
    private static final String PASSWORD = "Admin123";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            throw new SQLException("Database connection error.");
        }
        return conn;
    }


}
