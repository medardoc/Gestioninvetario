package Controlador;

import Modelo.ConexionBD;
import Modelo.VentasDiarias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentasDiariasOperacionesAdmin {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ConexionBD conectar = new ConexionBD();
    VentasDiarias v = new VentasDiarias();

    public List consultarFechaVentas() {
        List<VentasDiarias> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(
                    "	SELECT \n"
                    + "    V.FECHAVENTA as FECHA,\n"
                    + "    CAST(SUM(V.CANTIDAD * V.PRECIO) AS INT) AS TOTALVENTA\n"
                    + "FROM \n"
                    + "    VENTASDIARIAS V\n"
                    + "GROUP BY \n"
                    + "    V.FECHAVENTA\n"
                    + "ORDER BY \n"
                    + "    V.FECHAVENTA DESC;"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                VentasDiarias v = new VentasDiarias();
                v.setFechaVenta(rs.getString("FECHA")); // Utiliza el nombre de la columna
                v.setCantidadVD(rs.getInt("TOTALVENTA"));
                datos.add(v);

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
