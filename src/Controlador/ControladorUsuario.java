package Controlador;

import Modelo.Cliente;
import Modelo.Productos;
import Modelo.VentasDiarias;
import Modelo.Proveedores;
import Vista.InterfazAdmin;
import Vista.VistaUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author javie
 */
public class ControladorUsuario implements ActionListener {

    ProductosOperacionesUsuario productoOPus = new ProductosOperacionesUsuario();
    ProductosOperacionesAdmin productoOPAd = new ProductosOperacionesAdmin();
    ClientesOperacionesAdmin clienteOPAd = new ClientesOperacionesAdmin();
    VentasDiariasOperacionesAdmin ventasOPAd = new VentasDiariasOperacionesAdmin();
    ClienteAccionesUsuario clienteUsuarioACus = new ClienteAccionesUsuario();
    VentasDiariasAccionesUsuario ventasACUs = new VentasDiariasAccionesUsuario();
    ProveedorOperacionesUsuario proveedorOPus = new ProveedorOperacionesUsuario();
    Productos p = new Productos();
    Cliente c = new Cliente();
    Cliente cl = new Cliente();
    VentasDiarias v = new VentasDiarias();
    Proveedores pr = new Proveedores();
    InterfazAdmin interfazAdmin;

    VistaUsuario vistaUsuario;
    DefaultTableModel modeloProductoUs = new DefaultTableModel();
    DefaultTableModel modeloProductosUs = new DefaultTableModel();
    DefaultTableModel modeloclienteUS = new DefaultTableModel();
    DefaultTableModel modeloVentasDiariasUS = new DefaultTableModel();
    DefaultTableModel modeloCliUS = new DefaultTableModel();
    DefaultTableModel modeloventasACUs = new DefaultTableModel();
    DefaultTableModel modeloProveedorUs = new DefaultTableModel();

    public ControladorUsuario() {
    }

    public ControladorUsuario(VistaUsuario u) {
        this.vistaUsuario = u;

        //Botones
        this.vistaUsuario.btnConsCLIUs.addActionListener(this);

        this.vistaUsuario.btnSalirUs.addActionListener(this);

        this.vistaUsuario.btnConsProdUs.addActionListener(this);

        this.vistaUsuario.btnSalProdUs.addActionListener(this);
        this.vistaUsuario.btnConsultarEmRepUs.addActionListener(this);
        //Botones VentasDiarias
        this.vistaUsuario.btnConsultarVenDiarUs.addActionListener(this);

        this.vistaUsuario.btnSalirVenDiarUs.addActionListener(this);
        //BOTONES CLIENTE}
        this.vistaUsuario.btnConsCLIUs.addActionListener(this);
        //BOTONES proveedor
        this.vistaUsuario.btnConsProvUs.addActionListener(this);
        this.vistaUsuario.btnSalProvUs.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaUsuario.btnConsProdUs) {
            limpiarTablaProductoUs();
            consultarProductoUs(vistaUsuario.jTableProdUs);
        }

        if (e.getSource() == vistaUsuario.btnSalProdUs) {
            JOptionPane.showMessageDialog(vistaUsuario, "Adios");
            System.exit(0);
        }
        if (e.getSource() == vistaUsuario.btnConsultarEmRepUs) {
            limpiarTablaMenVen();
            limpiarTablaMasVen();
            limpiarTablaStockBajo();
            limpiarTablaClienteGasto();
            limpiarTablaFechaVenta();
            consultarMenVen(vistaUsuario.jTableMenosVendidoUs);
            consultarMasVen(vistaUsuario.jTableMasVendidoUs);
            consultarStockBajo(vistaUsuario.jTableStockBajoUs);
            consultarClienteGasto(vistaUsuario.jTableClienteGastosUs);
            consultarFechaVenta(vistaUsuario.jTableFechaVentasUs);
        }

        //Funciones botones VentasDiarias
        if (e.getSource() == vistaUsuario.btnConsultarVenDiarUs) {
            limpiarTablaVDUs();
            consultarVDUS(vistaUsuario.jTableVenDiarUs);

        }

        if (e.getSource() == vistaUsuario.btnSalirVenDiarUs) {
            JOptionPane.showMessageDialog(vistaUsuario, "Adios");
            System.exit(0);
        }

        //Funciones de botones Clientes
        if (e.getSource() == vistaUsuario.btnConsCLIUs) {
            limpiarTablaClienteUsuario();
            consultar(vistaUsuario.jTableCliUs);

        }
        if (e.getSource() == vistaUsuario.btnSalirUs) {
            JOptionPane.showMessageDialog(vistaUsuario, "Adios");
            System.exit(0);
        }
        //PROVEEDORES USUARIO
        //Proveedores
        if (e.getSource() == vistaUsuario.btnConsProvUs) {
            limpiarTablaProveedoresUs();
            consultarProveedoresUs(vistaUsuario.jTableProvUs);
        }
        
        if (e.getSource() == vistaUsuario.btnSalProvUs) {
            JOptionPane.showMessageDialog(vistaUsuario, "Cerrando Aplicación");
            System.exit(0);
        }

    }

    public void limpiarTablaMenVen() {
        DefaultTableModel model = (DefaultTableModel) vistaUsuario.jTableMenosVendidoUs.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaMasVen() {
        DefaultTableModel model = (DefaultTableModel) vistaUsuario.jTableMasVendidoUs.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaStockBajo() {
        DefaultTableModel model = (DefaultTableModel) vistaUsuario.jTableStockBajoUs.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaClienteGasto() {
        DefaultTableModel model = (DefaultTableModel) vistaUsuario.jTableClienteGastosUs.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaFechaVenta() {
        DefaultTableModel model = (DefaultTableModel) vistaUsuario.jTableFechaVentasUs.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaClienteUsuario() {
        DefaultTableModel model = (DefaultTableModel) vistaUsuario.jTableCliUs.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void consultarMenVen(JTable tabla) {

        modeloProductosUs = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProductosUs);
        try {

            List<Productos> lista = productoOPAd.consultarMenVen();
            modeloProductosUs.setRowCount(0);
            Object[] objeto = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getCodigoProducto();
                objeto[1] = lista.get(i).getNombreProducto();
                objeto[2] = lista.get(i).getStockProducto();
                modeloProductosUs.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultarMasVen(JTable tabla) {
        modeloProductosUs = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProductosUs);
        try {
            List<Productos> lista = productoOPAd.consultarmasven();
            modeloProductosUs.setRowCount(0);
            Object[] objeto = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getCodigoProducto();
                objeto[1] = lista.get(i).getNombreProducto();
                objeto[2] = lista.get(i).getStockProducto();
                modeloProductosUs.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultarStockBajo(JTable tabla) {

        modeloProductosUs = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProductosUs);
        try {

            List<Productos> lista = productoOPAd.consultarStockbajo();
            modeloProductosUs.setRowCount(0);
            Object[] objeto = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getCodigoProducto();
                objeto[1] = lista.get(i).getNombreProducto();
                objeto[2] = lista.get(i).getStockProducto();
                modeloProductosUs.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultarClienteGasto(JTable tabla) {

        modeloclienteUS = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloclienteUS);
        try {

            List<Cliente> lista = clienteOPAd.consultarClienteGasto();
            modeloclienteUS.setRowCount(0);
            Object[] objeto = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getRutCliente();
                objeto[1] = lista.get(i).getNombreCliente();
                objeto[2] = lista.get(i).getStock();
                modeloclienteUS.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultarFechaVenta(JTable tabla) {
        modeloVentasDiariasUS = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloVentasDiariasUS);
        try {
            limpiarTablaFechaVenta();
            List<VentasDiarias> lista = ventasOPAd.consultarFechaVentas();
            modeloVentasDiariasUS.setRowCount(0);
            Object[] objeto = new Object[2];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getFechaVenta();
                objeto[1] = lista.get(i).getCantidadVD();
                modeloVentasDiariasUS.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultarProductoUs(JTable tabla) {
        centrarCeldasProductosUs(tabla);
        modeloProductoUs = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProductoUs);
        try {
            limpiarTablaProductoUs();
            List<Productos> lista = productoOPus.consultar();
            modeloProductoUs.setRowCount(0);
            Object[] objeto = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getCodigoProducto();
                objeto[1] = lista.get(i).getNombreProducto();
                objeto[2] = lista.get(i).getStockProducto();
                modeloProductoUs.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultar(JTable tabla) {
        modeloCliUS = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloCliUS);
        try {
            List<Cliente> lista = clienteUsuarioACus.consultar();
            modeloCliUS.setRowCount(0);
            Object[] objeto = new Object[5];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getRutCliente();
                objeto[1] = lista.get(i).getNombreCliente();
                objeto[2] = lista.get(i).getApellidoCliente();
                objeto[3] = lista.get(i).getEmailCliente();
                objeto[4] = lista.get(i).getCelularCliente();
                modeloCliUS.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void limpiarTablaProductoUs() {
        DefaultTableModel model = (DefaultTableModel) vistaUsuario.jTableProdUs.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

    }

    public void limpiarTablaVDUs() {
        DefaultTableModel model = (DefaultTableModel) vistaUsuario.jTableVenDiarUs.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

    }

    public void centrarCeldasProductosUs(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vistaUsuario.jTableProdUs.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void centrarCeldasClienteUs(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vistaUsuario.jTableCliUs.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    // Metodos VentasDiariasUS
    public void consultarVDUS(JTable tabla) {
        centrarCeldasProductosUs(tabla);
        modeloventasACUs = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloventasACUs);
        try {
            limpiarTablaProductoUs();
            List<VentasDiarias> lista = ventasACUs.consultarVDUS();
            modeloventasACUs.setRowCount(0);
            Object[] objeto = new Object[5];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getIdClienteVD();
                objeto[1] = lista.get(i).getIdProductoVD();
                objeto[2] = lista.get(i).getCantidadVD();
                objeto[3] = lista.get(i).getNumeroVentasVD();
                objeto[4] = lista.get(i).getTipoPagoVD();

                modeloventasACUs.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }
    //METODOS PROVEEDOR
    
    public void limpiarTablaProveedoresUs() {
        DefaultTableModel model = (DefaultTableModel) vistaUsuario.jTableProvUs.getModel();
        int rowCount = model.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void consultarProveedoresUs(JTable tabla) {
        modeloProveedorUs = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProveedorUs);
        try {
            limpiarTablaProveedoresUs();
            List<Proveedores> lista = proveedorOPus.consultarProveedores();
            modeloProveedorUs.setRowCount(0);
            Object[] objeto = new Object[2];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getIdProveedores();
                objeto[1] = lista.get(i).getNombreProveedores();
                modeloProveedorUs.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

}
