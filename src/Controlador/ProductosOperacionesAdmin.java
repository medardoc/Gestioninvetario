package Controlador;

import Modelo.ConexionBD;
import Modelo.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductosOperacionesAdmin {

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

    public List consultarMenVen() {
        List<Productos> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(
                    "SELECT P.IDPRODUCTO, P.NOMBREPRODUCTO, CAST(SUM(V.CANTIDAD) AS INT) AS TOTAL_VENDIDO "
                    + "FROM VENTASDIARIAS V "
                    + "INNER JOIN PRODUCTOS P ON V.IDPRODUCTO = P.IDPRODUCTO "
                    + "GROUP BY P.IDPRODUCTO, P.NOMBREPRODUCTO "
                    + "ORDER BY TOTAL_VENDIDO ASC"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();
                p.setCodigoProducto(rs.getInt("IDPRODUCTO"));  // Utiliza el nombre de la columna
                p.setNombreProducto(rs.getString("NOMBREPRODUCTO"));
                p.setStockProducto(rs.getInt("TOTAL_VENDIDO"));  // Asegúrate de tener este atributo en la clase Productos
                datos.add(p);
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

    public List consultarmasven() {
        List<Productos> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(
                    "SELECT \n"
                    + "    P.IDPRODUCTO,\n"
                    + "    P.NOMBREPRODUCTO,\n"
                    + "    CAST(SUM(V.CANTIDAD) AS INT) AS TOTAL_VENDIDO\n"
                    + "FROM \n"
                    + "    VENTASDIARIAS V\n"
                    + "    INNER JOIN PRODUCTOS P ON V.IDPRODUCTO = P.IDPRODUCTO\n"
                    + "GROUP BY \n"
                    + "    P.IDPRODUCTO, P.NOMBREPRODUCTO\n"
                    + "ORDER BY \n"
                    + "    TOTAL_VENDIDO DESC;"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();
                p.setCodigoProducto(rs.getInt("IDPRODUCTO"));  // Utiliza el nombre de la columna
                p.setNombreProducto(rs.getString("NOMBREPRODUCTO"));
                p.setStockProducto(rs.getInt("TOTAL_VENDIDO"));  // Asegúrate de tener este atributo en la clase Productos
                datos.add(p);
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

    public List consultarStockbajo() {
        List<Productos> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(
                    "SELECT \n"
                    + "    P.IDPRODUCTO,\n"
                    + "    P.NOMBREPRODUCTO,\n"
                    + "    CAST(P.STOCK AS INT) AS STOCK\n"
                    + "FROM \n"
                    + "    PRODUCTOS P\n"
                    + "WHERE \n"
                    + "    P.STOCK <= 10\n"
                    + "ORDER BY \n"
                    + "    P.STOCK ASC;"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();
                p.setCodigoProducto(rs.getInt("IDPRODUCTO"));  // Utiliza el nombre de la columna
                p.setNombreProducto(rs.getString("NOMBREPRODUCTO"));
                p.setStockProducto(rs.getInt("STOCK"));  // Asegúrate de tener este atributo en la clase Productos
                datos.add(p);
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

   

    public int agregar(Productos productos) {
        int r = 0;
        String sql = "insert into PRODUCTOS ( IDPRODUCTO, NOMBREPRODUCTO, STOCK) values (?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, productos.getCodigoProducto());
            ps.setString(2, productos.getNombreProducto());
            ps.setInt(3, productos.getStockProducto());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;

            } else {
                return 0;
            }
        } catch (Exception e) {
        }
        return r;

    }

    public int modificar(Productos productos) {
        int r = 0;
        String sql = "update PRODUCTOS set IDPRODUCTO=?, NOMBREPRODUCTO=?, STOCK=? where IDPRODUCTO=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, productos.getCodigoProducto());
            ps.setString(2, productos.getNombreProducto());
            ps.setInt(3, productos.getStockProducto());
            ps.setInt(4, productos.getCodigoProducto());

            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {

                return 0;
            }

        } catch (Exception e) {

        }
        return r;

    }

    public int eliminar(String codigoProducto) {
        int r = 0;
        String sql = "delete from PRODUCTOS where IDPRODUCTO=" + "'" + codigoProducto + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;

    }

}
