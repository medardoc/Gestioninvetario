package Controlador;

import Modelo.ConexionBD;
import Modelo.VentasDiarias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentasDiariasAccionesAdmin {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection con;
    private ConexionBD conectar = new ConexionBD();

    public List<VentasDiarias> consultar() {
        List<VentasDiarias> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement("SELECT IDCLIENTE, IDPRODUCTO, CANTIDAD, NROVENTA, TIPOPAGO FROM VENTASDIARIAS");
            rs = ps.executeQuery();
            while (rs.next()) {
                VentasDiarias venta = new VentasDiarias();
                venta.setIdClienteVD(rs.getInt("IdCliente"));
                venta.setIdProductoVD(rs.getInt("IdProducto"));
                venta.setCantidadVD(rs.getInt("Cantidad"));
                venta.setNumeroVentasVD(rs.getInt("NROVENTA"));
                venta.setTipoPagoVD(rs.getString("TipoPago"));
                datos.add(venta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Salir();
        }
        return datos;
    }

    public int agregar(VentasDiarias venta) {
        int r = 0;
        String sql = "INSERT INTO VENTASDIARIAS (IDCLIENTE, IDPRODUCTO, CANTIDAD, NROVENTA, TIPOPAGO) VALUES (?, ?, ?, ?, ?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta.getIdClienteVD());
            ps.setInt(2, venta.getIdProductoVD());
            ps.setInt(3, venta.getCantidadVD());
            ps.setInt(4, venta.getNumeroVentasVD());
            ps.setString(5, venta.getTipoPagoVD());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Salir();
        }
        return r;
    }

    public int modificar(VentasDiarias venta) {
        int r = 0;
        String sql = "UPDATE VENTASDIARIAS SET IDPRODUCTO = ?, CANTIDAD = ?, IDCLIENTE = ?, TIPOPAGO = ? WHERE NROVENTA = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta.getIdProductoVD());
            ps.setInt(2, venta.getCantidadVD());
            ps.setInt(3, venta.getIdClienteVD());
            ps.setString(4, venta.getTipoPagoVD());
            ps.setInt(5, venta.getNumeroVentasVD());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Salir();
        }
        return r;
    }

    public int eliminar(int idCliente) {
        int r = 0;
        String sql = "DELETE FROM VENTASDIARIAS WHERE NROVENTA = ?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Salir();
        }
        return r;
    }

    private void Salir() {
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
}
