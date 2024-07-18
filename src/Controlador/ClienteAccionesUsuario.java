package Controlador;

import Modelo.ConexionBD;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteAccionesUsuario {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection con;
    private ConexionBD conectar = new ConexionBD();

    public List<Cliente> consultar() {
        List<Cliente> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement("SELECT RUT, NOMBRECLIENTE, APELLIDOPATERNO, EMAIL, CELULAR1 FROM CLIENTES");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setRutCliente(rs.getInt("RUT"));
                cliente.setNombreCliente(rs.getString("NOMBRECLIENTE"));
                cliente.setApellidoCliente(rs.getString("APELLIDOPATERNO"));
                cliente.setEmailCliente(rs.getString("EMAIL"));
                cliente.setCelularCliente(rs.getString("CELULAR1"));
                datos.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            salir();
        }
        return datos;
    }

    public void limpiar(javax.swing.JTextField... campos) {
        for (javax.swing.JTextField campo : campos) {
            campo.setText("");
        }
    }

    public void salir() {
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
