/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

// Conexion de SQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Lectura de archivos o propiedades
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author g.perezmoreno
 */
public class ConexionDatabase {
    
    private static Connection conexion;
    
    public static Connection getConnection() {
        
        if (conexion == null) {
            
            try {
                
                Properties props = new Properties();
                
                props.load(new FileInputStream("db.properties"));
                
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                conexion = DriverManager.getConnection(url, user, password);
                
                System.out.println("Conexion Exitosa!");
                
            } catch (SQLException | IOException error) {
                System.out.println("Error message: " + error);
                throw new RuntimeException("Fallo la conexion a la base de datos." + error);
                // error.printStackTrace();
            }
            
        }
        
        return conexion;
    }
    
    public static void closeConnection() {
    
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexion a la base de datos cerrada.");
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
        
    }
    
}
