package Controlador;

import Modelo.ConexionBD;
import Modelo.Proveedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorOperacionesUsuario {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection con;
    private ConexionBD conectar = new ConexionBD();

    public List<Proveedores> consultarProveedores() {
        List<Proveedores> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement("SELECT IDPROVEEDOR, NOMBREPROVEEDOR FROM dbo.PROVEEDORES");
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedores proveedor = new Proveedores();
                proveedor.setIdProveedores(rs.getInt("IDPROVEEDOR"));
                proveedor.setNombreProveedores(rs.getString("NOMBREPROVEEDOR"));
                datos.add(proveedor);
            }
        } catch (Exception e) {
            e.printStackTrace();  // Para depuración
        } finally {
            cerrarRecursos();
        }
        return datos;
    }

    public void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
