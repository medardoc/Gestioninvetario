/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConexionBD;
import Modelo.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javie
 */
public class ProductosOperacionesUsuario {
      PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ConexionBD conectar = new ConexionBD();
    Productos p = new Productos();

    public List consultar() {
        List<Productos> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement("SELECT IDPRODUCTO, NOMBREPRODUCTO, STOCK FROM PRODUCTOS");
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();
                p.setCodigoProducto(rs.getInt(1));  // Cambié a rs.getInt("IDPRODUCTO")
                p.setNombreProducto(rs.getString(2));
                p.setStockProducto(rs.getInt(3));                
                datos.add(p);
            }

        } catch (Exception e) {
              e.printStackTrace();  // Añadí para depuración
        }
        return datos;
    }
}
