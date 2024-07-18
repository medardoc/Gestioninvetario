package Controlador;

import Modelo.ConexionBD;
import Modelo.Proveedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorOperacionesAdmin {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection con;
    private ConexionBD conectar = new ConexionBD();

    public List<Proveedores> consultar() {
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

    public int agregar(Proveedores proveedor) {
        int r = 0;
        String sql = "INSERT INTO PROVEEDORES (IDPROVEEDOR, NOMBREPROVEEDOR) VALUES (?, ?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, proveedor.getIdProveedores());
            ps.setString(2, proveedor.getNombreProveedores());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // Para depuración
        } finally {
            cerrarRecursos();
        }
        return r;
    }

    public int modificar(Proveedores proveedor) {
        int r = 0;
        String sql = "UPDATE PROVEEDORES SET NOMBREPROVEEDOR = ? WHERE IDPROVEEDOR = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getNombreProveedores());
            ps.setInt(2, proveedor.getIdProveedores());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // Para depuración
        } finally {
            cerrarRecursos();
        }
        return r;
    }

    public int eliminar(int idProveedor) {
        int r = 0;
        String sql = "DELETE FROM PROVEEDORES WHERE IDPROVEEDOR = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProveedor);
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // Para depuración
        } finally {
            cerrarRecursos();
        }
        return r;
    }

    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
