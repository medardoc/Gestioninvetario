package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public static Connection getConnection() {
        String url = "jdbc:sqlserver://LAPTOP-3OK1G48E:1433;" +  // nombre del servidor y puerto
                     "database=LA_TIENDA;" +           // nombre de la base de datos
                     "user=Momonga;" +                 // nombre de usuario
                     "password=123456;";             
                          

        try {
            Connection con = DriverManager.getConnection(url);
            System.out.println("Conexión exitosa a la base de datos");
            return con;
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.toString());
            return null;
        }
    }
}

