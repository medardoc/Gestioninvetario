package Controlador;

import Modelo.ConexionBD;
import Modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientesOperacionesAdmin {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ConexionBD conectar = new ConexionBD();
    Cliente c = new Cliente();
   

    public List consultarClienteGasto() {
        List<Cliente> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(
                    "SELECT \n"
                    + "    C.IDCLIENTE,\n"
                    + "    C.NOMBRECLIENTE,\n"
                    + "    CAST(SUM(V.CANTIDAD * V.PRECIO) AS INT) AS TOTAL_GASTADO\n"
                    + "FROM \n"
                    + "    CLIENTES C\n"
                    + "    INNER JOIN VENTASDIARIAS V ON C.IDCLIENTE = V.IDCLIENTE\n"
                    + "GROUP BY \n"
                    + "    C.IDCLIENTE, C.NOMBRECLIENTE, C.APELLIDOPATERNO, C.APELLIDOMATERNO\n"
                    + "ORDER BY \n"
                    + "    TOTAL_GASTADO DESC;"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                 
                
                c.setRutCliente(rs.getInt("IDCLIENTE"));  // Utiliza el nombre de la columna
                c.setNombreCliente(rs.getString("NOMBRECLIENTE"));
                c.setStock(rs.getInt("TOTAL_GASTADO"));  // Asegúrate de tener este atributo en la clase Productos
                datos.add(c);
               
            }
        } catch (Exception e) {
            e.printStackTrace();  // Añadí para depuración
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return datos;
    }
}
