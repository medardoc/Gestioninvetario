package Controlador;

import Modelo.ConexionBD;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteAccionesAdmin {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection con;
    private ConexionBD conectar = new ConexionBD();

    public List<Cliente> consultarCliente() {
        List<Cliente> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement("SELECT IDCLIENTE, RUT, NOMBRECLIENTE, APELLIDOPATERNO, EMAIL, CELULAR1 FROM CLIENTES");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IDCLIENTE"));
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
            salirCliente();
        }
        return datos;
    }

    private void salirCliente() {
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

    public int agregarCliente(Cliente cliente) {
        int r = 0;
        String sql = "INSERT INTO CLIENTES (IDCLIENTE, RUT, NOMBRECLIENTE, APELLIDOPATERNO, EMAIL, CELULAR1) VALUES (?, ?, ?, ?, ?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getRutCliente());
            ps.setInt(2, cliente.getRutCliente());
            ps.setString(3, cliente.getNombreCliente());
            ps.setString(4, cliente.getApellidoCliente());
            ps.setString(5, cliente.getEmailCliente());
            ps.setString(6, cliente.getCelularCliente());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            salirCliente();
        }
        return r;
    }

    public int eliminarCliente(int idCliente) {
        int r = 0;
        String sql = "DELETE FROM CLIENTES WHERE IDCLIENTE = ?";
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public int modificarCliente(Cliente cliente) {
        int r = 0;
        String sql = "UPDATE CLIENTES SET RUT = ?, NOMBRECLIENTE = ?, APELLIDOPATERNO = ?, EMAIL = ?, CELULAR1= ? WHERE IDCLIENTE = ?";
        try {

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getRutCliente());
            ps.setString(2, cliente.getNombreCliente());
            ps.setString(3, cliente.getApellidoCliente());
            ps.setString(4, cliente.getEmailCliente());
            ps.setString(5, cliente.getCelularCliente());
             ps.setInt(6, cliente.getIdCliente());
            r = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            salirCliente();
        }
        return r;
    }

}
