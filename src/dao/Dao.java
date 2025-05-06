/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author T
 */
public class Dao  {
    protected Connection con;
    
    public Dao() {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://localhost:1433;trustServerCertificate=true;encrypt=false;databaseName=TSBD";
            String username = "sa";
            String password = "123456789";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}
