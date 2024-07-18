package Controlador;

import Modelo.ConexionBD;
import Modelo.VentasDiarias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentasDiariasAccionesUsuario {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection con;
    private ConexionBD conectar = new ConexionBD();

    public List<VentasDiarias> consultarVDUS() {
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
            salir();
        }
        return datos;
    }

    public void limpiarTexto(javax.swing.JTextField... campos) {
        for (javax.swing.JTextField campo : campos) {
            campo.setText("");
        }
    }  

    public void salir() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

