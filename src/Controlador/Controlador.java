package Controlador;

import Modelo.Productos;
import Modelo.VentasDiarias;
import Modelo.Cliente;
import Modelo.Proveedores;
import Vista.InterfazAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {

    ProductosOperacionesAdmin productoOPAd = new ProductosOperacionesAdmin();
    ClientesOperacionesAdmin clienteOPAd = new ClientesOperacionesAdmin();
    ClienteAccionesAdmin clienteACAd = new ClienteAccionesAdmin();
    VentasDiariasOperacionesAdmin ventasOPAd = new VentasDiariasOperacionesAdmin();
    VentasDiariasAccionesAdmin ventasACAd = new VentasDiariasAccionesAdmin();
    ProveedorOperacionesAdmin proveedorOpAd = new ProveedorOperacionesAdmin(); // Proveedor
    Proveedores pv = new Proveedores();
    Productos p = new Productos();
    Cliente c = new Cliente();
    Cliente cl = new Cliente();
    VentasDiarias v = new VentasDiarias();
    InterfazAdmin interfazAdmin;
    
   
    DefaultTableModel modeloProductosAd = new DefaultTableModel();
    DefaultTableModel modeloclienteAd = new DefaultTableModel();
    DefaultTableModel modeloVentasDiarias = new DefaultTableModel();
    DefaultTableModel modeloProveedorAd = new DefaultTableModel(); // Proveedor ADMIN
    DefaultTableModel modeloventasACAd = new DefaultTableModel();
    DefaultTableModel modeloclienteACAd = new DefaultTableModel();

    public Controlador() {
    }

    public Controlador(InterfazAdmin a) {
        this.interfazAdmin = a;
        
        //Botones Clientes Admin
        this.interfazAdmin.btnConsultarCliente.addActionListener(this);
        this.interfazAdmin.btnAgregarCliente.addActionListener(this);
        this.interfazAdmin.btnLimpiarCliente.addActionListener(this);
        this.interfazAdmin.btnGuardarCliente.addActionListener(this);
        this.interfazAdmin.btnModificarCliente.addActionListener(this);
        this.interfazAdmin.btnSalirCliente.addActionListener(this);
        this.interfazAdmin.btnEliminarCliente.addActionListener(this);

        //Botones Productos
        this.interfazAdmin.btnAgregarProducto.addActionListener(this);
        this.interfazAdmin.btnLimpiarProducto.addActionListener(this);
        this.interfazAdmin.btnConsultarProducto.addActionListener(this);
        this.interfazAdmin.btnModificarProducto.addActionListener(this);
        this.interfazAdmin.btnSalirProducto.addActionListener(this);
        this.interfazAdmin.btnGuardarProducto.addActionListener(this);
        this.interfazAdmin.btnConsultarEmRep.addActionListener(this);
        this.interfazAdmin.btnElimProducto.addActionListener(this);
        // Botones Proveedores ADMIN    
        this.interfazAdmin.btnAgregarProve.addActionListener(this);
        this.interfazAdmin.btnLimpiarProve.addActionListener(this);
        this.interfazAdmin.btnConsultarProve.addActionListener(this);
        this.interfazAdmin.btnModificarProve.addActionListener(this);
        this.interfazAdmin.btnSalirProve.addActionListener(this);
        this.interfazAdmin.btnGuardarProve.addActionListener(this);
        this.interfazAdmin.btnElimProve.addActionListener(this);
        // Botones VentasDiarias
        this.interfazAdmin.btnAgregarVenDiar.addActionListener(this);
        this.interfazAdmin.btnLimpiarVenDiar.addActionListener(this);
        this.interfazAdmin.btnConsultarVenDiar.addActionListener(this);
        this.interfazAdmin.btnModificarVenDiar.addActionListener(this);
        this.interfazAdmin.btnSalirVenDiar.addActionListener(this);
        this.interfazAdmin.btnGuardarVenDiar.addActionListener(this);
        this.interfazAdmin.btnEliminarVenDiar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == interfazAdmin.btnSalirProducto) {
            JOptionPane.showMessageDialog(interfazAdmin, "Cerrando aplicacion");
            System.exit(0);
        }
        if (e.getSource() == interfazAdmin.btnAgregarProducto) {
            agregarProducto();
            limpiarTablaProducto();
            consultarProducto(interfazAdmin.jTableProducto);
            limpiarProducto();

        }
        if (e.getSource() == interfazAdmin.btnConsultarProducto) {
            limpiarTablaProducto();
            consultarProducto(interfazAdmin.jTableProducto);
            limpiarProducto();
        }
        if (e.getSource() == interfazAdmin.btnModificarProducto) {
            int fila = interfazAdmin.jTableProducto.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(interfazAdmin, "Debe Seleccionar una fila");

            } else {
                int codigoProducto = Integer.parseInt(interfazAdmin.jTableProducto.getValueAt(fila, 0).toString());
                String nombreProducto = (String) interfazAdmin.jTableProducto.getValueAt(fila, 1);
                int stockProducto = Integer.parseInt(interfazAdmin.jTableProducto.getValueAt(fila, 2).toString());
                interfazAdmin.txtCodigoProducto.setText(String.valueOf(codigoProducto));
                interfazAdmin.txtNombreProducto.setText(nombreProducto);
                interfazAdmin.txtStockProducto.setText(String.valueOf(stockProducto));
            }
        }
        if (e.getSource() == interfazAdmin.btnLimpiarProducto) {
            limpiarProducto();
        }
        if (e.getSource() == interfazAdmin.btnGuardarProducto) {
            guardarProducto();
            consultarProducto(interfazAdmin.jTableProducto);
            limpiarProducto();

        }
        if (e.getSource() == interfazAdmin.btnElimProducto) {
            eliminarProducto();
            limpiarTablaProducto();
            consultarProducto(interfazAdmin.jTableProducto);
            limpiarProducto();
        }

        //Boton para Informes de ventas
        if (e.getSource() == interfazAdmin.btnConsultarEmRep) {
            limpiarTablaMenVen();
            limpiarTablaMasVen();
            limpiarTablaStockBajo();
            limpiarTablaClienteGasto();
            limpiarTablaFechaVenta();
            consultarMenVen(interfazAdmin.jTableMenosVendido);
            consultarMasVen(interfazAdmin.jTableMasVendido);
            consultarStockBajo(interfazAdmin.jTableStockBajo);
            consultarClienteGasto(interfazAdmin.jTableClienteGastos);
            consultarFechaVenta(interfazAdmin.jTableFechaVentas);

        }
        // Botones para proveedores
        if (e.getSource() == interfazAdmin.btnAgregarProve) {
            agregarProveedor();
            limpiarTablaProveedor();
            consultarProveedor(interfazAdmin.jTableProve);
            limpiarProveedor();
        }
        if (e.getSource() == interfazAdmin.btnConsultarProve) {
            limpiarTablaProveedor();
            consultarProveedor(interfazAdmin.jTableProve);
            limpiarProveedor();
        }
        if (e.getSource() == interfazAdmin.btnModificarProve) {
            modificarProveedor();
        }
        if (e.getSource() == interfazAdmin.btnLimpiarProve) {
            limpiarProveedor();
        }
        if (e.getSource() == interfazAdmin.btnGuardarProve) {
            guardarProve();
            consultarProveedor(interfazAdmin.jTableProve);
            limpiarProveedor();
        }
        if (e.getSource() == interfazAdmin.btnSalirProve) {
            JOptionPane.showMessageDialog(interfazAdmin, "Cerrando aplicacion ");
            System.exit(0);
        }
        if (e.getSource() == interfazAdmin.btnElimProve) {
            eliminarProveedor();
            limpiarTablaProveedor();
            consultarProveedor(interfazAdmin.jTableProve);
            limpiarProveedor();
        }
        //Botones de Ventas Diarias
        if (e.getSource() == interfazAdmin.btnConsultarVenDiar) {
            limpiarTablaVentasDiarias();
            consultarVentasDiarias(interfazAdmin.jTableVenDiar);
            limpiarVentasDiarias();

        }
        if (e.getSource() == interfazAdmin.btnSalirVenDiar) {
            System.exit(0);
        }
        if (e.getSource() == interfazAdmin.btnAgregarVenDiar) {
            agregarVentaDiaria();
            limpiarTablaVentasDiarias();
            consultarVentasDiarias(interfazAdmin.jTableVenDiar);
            limpiarVentasDiarias();

        }

        if (e.getSource() == interfazAdmin.btnModificarVenDiar) {
            int fila = interfazAdmin.jTableVenDiar.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(interfazAdmin, "Debe Seleccionar una fila");

            } else {
                int idClienteVD = Integer.parseInt(interfazAdmin.jTableVenDiar.getValueAt(fila, 0).toString());
                int idProductoVD = Integer.parseInt(interfazAdmin.jTableVenDiar.getValueAt(fila, 1).toString());
                int cantidadVD = Integer.parseInt(interfazAdmin.jTableVenDiar.getValueAt(fila, 2).toString());
                int numeroVentasVD = Integer.parseInt(interfazAdmin.jTableVenDiar.getValueAt(fila, 3).toString());
                String tipoPagoVD = (String) interfazAdmin.jTableVenDiar.getValueAt(fila, 4);
                interfazAdmin.txtIDCliVenDiar.setText(String.valueOf(idClienteVD));
                interfazAdmin.txtIDProdVenDiar.setText(String.valueOf(idProductoVD));
                interfazAdmin.txtCantidadVenDiar.setText(String.valueOf(cantidadVD));
                interfazAdmin.txtNumVenDiar.setText(String.valueOf(numeroVentasVD));
                interfazAdmin.txtTipoPagVenDiar.setText(tipoPagoVD);
            }
        }
        if (e.getSource() == interfazAdmin.btnLimpiarVenDiar) {
            limpiarVentasDiarias();
        }
        if (e.getSource() == interfazAdmin.btnGuardarVenDiar) {
            guardarVentaDiaria();
            consultarVentasDiarias(interfazAdmin.jTableVenDiar);
            limpiarVentasDiarias();

        }
        if (e.getSource() == interfazAdmin.btnEliminarVenDiar) {
            eliminarVentaDiaria();
            limpiarTablaVentasDiarias();
            consultarVentasDiarias(interfazAdmin.jTableVenDiar);
            limpiarVentasDiarias();
        }

    
    
    //Botones de Clientes
    
        if (e.getSource() == interfazAdmin.btnConsultarCliente) {
            limpiarTablaCliente();
            consultarCliente(interfazAdmin.jTableCliente);
            limpiarCliente();

        }

        if (e.getSource()
                == interfazAdmin.btnSalirCliente) {
            System.exit(0);
        }

        if (e.getSource()
                == interfazAdmin.btnAgregarCliente) {
            agregarCliente();
            limpiarTablaCliente();
            consultarCliente(interfazAdmin.jTableCliente);
            limpiarCliente();

        }

        if (e.getSource()
                == interfazAdmin.btnModificarCliente) {
            int fila = interfazAdmin.jTableCliente.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(interfazAdmin, "Debe Seleccionar una fila");

            } else {
                int idCliente = Integer.parseInt(interfazAdmin.jTableCliente.getValueAt(fila, 0).toString());
                int rutCliente = Integer.parseInt(interfazAdmin.jTableCliente.getValueAt(fila, 1).toString());
                String nombreCliente = (String)interfazAdmin.jTableCliente.getValueAt(fila, 2);
                String apellidoCliente = (String)interfazAdmin.jTableCliente.getValueAt(fila, 3);
                String emailCliente = (String)interfazAdmin.jTableCliente.getValueAt(fila, 4);
                String celularCliente = (String)interfazAdmin.jTableCliente.getValueAt(fila, 5);
                
                interfazAdmin.txtIDCliente.setText(String.valueOf(idCliente));
                interfazAdmin.txtRUTCliente.setText(String.valueOf(rutCliente));
                interfazAdmin.txtNombreCliente.setText(nombreCliente);
                interfazAdmin.txtApellidoCliente.setText(apellidoCliente);
                interfazAdmin.txtEmailCliente.setText(emailCliente);
                interfazAdmin.txtCelularCliente.setText(celularCliente);
            }
        }

        if (e.getSource()
                == interfazAdmin.btnLimpiarCliente) {
            limpiarCliente();
        }

        if (e.getSource()
                == interfazAdmin.btnGuardarCliente) {
            guardarCliente();
            consultarCliente(interfazAdmin.jTableCliente);
            limpiarCliente();

        }

        if (e.getSource()
                == interfazAdmin.btnEliminarCliente) {
            eliminarCliente();
            limpiarTablaCliente();
            consultarCliente(interfazAdmin.jTableCliente);
            limpiarCliente();

        }
    }

    public void consultarProducto(JTable tabla) {
        centrarCeldasProductos(tabla);
        modeloProductosAd = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProductosAd);
        try {
            limpiarTablaProducto();
            List<Productos> lista = productoOPAd.consultar();
            modeloProductosAd.setRowCount(0);
            Object[] objeto = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getCodigoProducto();
                objeto[1] = lista.get(i).getNombreProducto();
                objeto[2] = lista.get(i).getStockProducto();
                modeloProductosAd.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultarMenVen(JTable tabla) {

        modeloProductosAd = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProductosAd);
        try {

            List<Productos> lista = productoOPAd.consultarMenVen();
            modeloProductosAd.setRowCount(0);
            Object[] objeto = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getCodigoProducto();
                objeto[1] = lista.get(i).getNombreProducto();
                objeto[2] = lista.get(i).getStockProducto();
                modeloProductosAd.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultarMasVen(JTable tabla) {

        modeloProductosAd = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProductosAd);
        try {

            List<Productos> lista = productoOPAd.consultarmasven();
            modeloProductosAd.setRowCount(0);
            Object[] objeto = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getCodigoProducto();
                objeto[1] = lista.get(i).getNombreProducto();
                objeto[2] = lista.get(i).getStockProducto();
                modeloProductosAd.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultarStockBajo(JTable tabla) {
        centrarCeldasProductos(tabla);
        modeloProductosAd = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProductosAd);
        try {

            List<Productos> lista = productoOPAd.consultarStockbajo();
            modeloProductosAd.setRowCount(0);
            Object[] objeto = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getCodigoProducto();
                objeto[1] = lista.get(i).getNombreProducto();
                objeto[2] = lista.get(i).getStockProducto();
                modeloProductosAd.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultarClienteGasto(JTable tabla) {
        modeloclienteAd = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloclienteAd);
        try {

            List<Cliente> lista = clienteOPAd.consultarClienteGasto();
            modeloclienteAd.setRowCount(0);
            Object[] objeto = new Object[3];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getRutCliente();
                objeto[1] = lista.get(i).getNombreCliente();
                objeto[2] = lista.get(i).getStock();
                modeloclienteAd.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void consultarFechaVenta(JTable tabla) {
          
        modeloVentasDiarias = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloVentasDiarias);
        try {
            limpiarTablaFechaVenta();
            List<VentasDiarias> lista = ventasOPAd.consultarFechaVentas();
            modeloVentasDiarias.setRowCount(0);
            Object[] objeto = new Object[2];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getFechaVenta();
                objeto[1] = lista.get(i).getCantidadVD();
                modeloVentasDiarias.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }
    

    public void agregarProducto() {
        int codigoProducto = Integer.parseInt(interfazAdmin.txtCodigoProducto.getText());
        String nombreProducto = interfazAdmin.txtNombreProducto.getText();
        int stockProducto = Integer.parseInt(interfazAdmin.txtStockProducto.getText());
        p.setCodigoProducto(codigoProducto);
        p.setNombreProducto(nombreProducto);
        p.setStockProducto(stockProducto);
        try {
            if (stockProducto == 0) {
                JOptionPane.showMessageDialog(interfazAdmin, "Error.");
            } else {
                int r = productoOPAd.agregar(p);
                if (r == 1) {
                    JOptionPane.showMessageDialog(interfazAdmin, "ProductoAgregado!");

                } else {
                    JOptionPane.showMessageDialog(interfazAdmin, "Producto no agregado..");
                }
                limpiarTablaProducto();
            }
        } catch (Exception e) {
        }
    }

    public void limpiarTablaProducto() {
        DefaultTableModel model = (DefaultTableModel) interfazAdmin.jTableProducto.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaVentasDiarias() {
        DefaultTableModel model = (DefaultTableModel) interfazAdmin.jTableVenDiar.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaMenVen() {
        DefaultTableModel model = (DefaultTableModel) interfazAdmin.jTableMenosVendido.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaMasVen() {
        DefaultTableModel model = (DefaultTableModel) interfazAdmin.jTableMasVendido.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaStockBajo() {
        DefaultTableModel model = (DefaultTableModel) interfazAdmin.jTableStockBajo.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaClienteGasto() {
        DefaultTableModel model = (DefaultTableModel) interfazAdmin.jTableClienteGastos.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarTablaFechaVenta() {
        DefaultTableModel model = (DefaultTableModel) interfazAdmin.jTableFechaVentas.getModel();
        int rowCount = model.getRowCount();

        // Eliminar todas las filas de la tabla comenzando desde la última
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarProducto() {
        interfazAdmin.txtCodigoProducto.setText("");
        interfazAdmin.txtNombreProducto.setText("");
        interfazAdmin.txtStockProducto.setText("");

    }

    public void limpiarVentasDiarias() {
        interfazAdmin.txtIDCliVenDiar.setText("");
        interfazAdmin.txtIDProdVenDiar.setText("");
        interfazAdmin.txtCantidadVenDiar.setText("");
        interfazAdmin.txtNumVenDiar.setText("");
        interfazAdmin.txtTipoPagVenDiar.setText("");

    }
    
    public void limpiarCliente() {
        interfazAdmin.txtIDCliente.setText("");
        interfazAdmin.txtRUTCliente.setText("");
        interfazAdmin.txtNombreCliente.setText("");
        interfazAdmin.txtApellidoCliente.setText("");
        interfazAdmin.txtEmailCliente.setText("");
        interfazAdmin.txtCelularCliente.setText("");

    }

    public void centrarCeldasProductos(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < interfazAdmin.jTableProducto.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void centrarCeldasVentasDiarias(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < interfazAdmin.jTableVenDiar.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void centrarCeldasVentasDiariasFechaVentas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < interfazAdmin.jTableFechaVentas.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void guardarProducto() {

        if (interfazAdmin.txtCodigoProducto.equals("")) {
            JOptionPane.showMessageDialog(interfazAdmin, "No se identifica Codigo...");

        } else {
            int codigoProducto = Integer.parseInt(interfazAdmin.txtCodigoProducto.getText());
            String nombreProducto = interfazAdmin.txtNombreProducto.getText();
            int stockProducto = Integer.parseInt(interfazAdmin.txtStockProducto.getText());
            p.setCodigoProducto(codigoProducto);
            p.setNombreProducto(nombreProducto);
            p.setStockProducto(stockProducto);
            if (codigoProducto < 0 || nombreProducto.equals("") || stockProducto < 0) {
                JOptionPane.showMessageDialog(interfazAdmin, "Error, campos vacios");
            } else {
                int r = productoOPAd.modificar(p);
                if (r == 1) {
                    JOptionPane.showMessageDialog(interfazAdmin, "Producto actualizado.");

                } else {
                    JOptionPane.showMessageDialog(interfazAdmin, "Error.");
                }
            }
        }

    }

    public void eliminarProducto() {
        int fila = interfazAdmin.jTableProducto.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(interfazAdmin, "Debes seleccionar una fila.");
        } else {
            String codigo = interfazAdmin.jTableProducto.getValueAt(fila, 0).toString();
            productoOPAd.eliminar(codigo);
            System.err.println("Pais Eliminado con codigo " + codigo);
            JOptionPane.showMessageDialog(interfazAdmin, "Producto con codigo " + codigo + " Eliminado.");
        }
        limpiarTablaProducto();
    }

    // Métodos para Proveedores
    public void guardarProve() {
        if (interfazAdmin.txtIDProve.getText().equals("")) {
            JOptionPane.showMessageDialog(interfazAdmin, "No se identifica Codigo...");
        } else {
            int idProveedor = Integer.parseInt(interfazAdmin.txtIDProve.getText());
            String nombreProveedor = interfazAdmin.txtNomProve.getText();
            pv.setIdProveedores(idProveedor);
            pv.setNombreProveedores(nombreProveedor);

            if (idProveedor < 0 || nombreProveedor.equals("")) {
                JOptionPane.showMessageDialog(interfazAdmin, "Error, campos vacios");
            } else {
                int r = proveedorOpAd.modificar(pv);
                if (r == 1) {
                    JOptionPane.showMessageDialog(interfazAdmin, "Proveedor actualizado.");
                    limpiarTablaProveedor();
                    consultarProveedor(interfazAdmin.jTableProve);
                    limpiarProveedor();
                } else {
                    JOptionPane.showMessageDialog(interfazAdmin, "Error.");
                }
            }
        }

    }

    public void consultarProveedor(JTable tabla) {
        centrarCeldasProveedores(tabla);
        modeloProveedorAd = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloProveedorAd);
        try {
            limpiarTablaProveedor();
            List<Proveedores> lista = proveedorOpAd.consultar();
            modeloProveedorAd.setRowCount(0);
            Object[] objeto = new Object[2];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getIdProveedores();
                objeto[1] = lista.get(i).getNombreProveedores();
                modeloProveedorAd.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void agregarProveedor() {
        int idProveedor = Integer.parseInt(interfazAdmin.txtIDProve.getText());
        String nombreProveedor = interfazAdmin.txtNomProve.getText();
        pv.setIdProveedores(idProveedor);
        pv.setNombreProveedores(nombreProveedor);
        try {
            int r = proveedorOpAd.agregar(pv);
            if (r == 1) {
                JOptionPane.showMessageDialog(interfazAdmin, "Proveedor Agregado!");
            } else {
                JOptionPane.showMessageDialog(interfazAdmin, "Proveedor no agregado.");
            }
            limpiarTablaProveedor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificarProveedor() {
        int fila = interfazAdmin.jTableProve.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(interfazAdmin, "Debe Seleccionar una fila");
        } else {
            int idProveedor = Integer.parseInt(interfazAdmin.jTableProve.getValueAt(fila, 0).toString());
            String nombreProveedor = (String) interfazAdmin.jTableProve.getValueAt(fila, 1);
            interfazAdmin.txtIDProve.setText(String.valueOf(idProveedor));
            interfazAdmin.txtNomProve.setText(nombreProveedor);
        }
    }

    public void limpiarTablaProveedor() {
        DefaultTableModel model = (DefaultTableModel) interfazAdmin.jTableProve.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    
    public void limpiarTablaCliente() {
        DefaultTableModel model = (DefaultTableModel) interfazAdmin.jTableCliente.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void limpiarProveedor() {
        interfazAdmin.txtIDProve.setText("");
        interfazAdmin.txtNomProve.setText("");
    }

    public void centrarCeldasProveedores(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void eliminarProveedor() {
        int fila = interfazAdmin.jTableProve.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(interfazAdmin, "Debes seleccionar una fila.");
        } else {
            int codigo = Integer.parseInt(interfazAdmin.jTableProve.getValueAt(fila, 0).toString());
            proveedorOpAd.eliminar(codigo);
            System.err.println("Pais Eliminado con codigo " + codigo);
            JOptionPane.showMessageDialog(interfazAdmin, "Proveedor con codigo " + codigo + " Eliminado.");
        }
        limpiarTablaProveedor();
    }

    // Metodos principales de VentasDiarias
    public void consultarVentasDiarias(JTable tabla) {
        modeloventasACAd = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloventasACAd);
        try {
            List<VentasDiarias> lista = ventasACAd.consultar();
            modeloventasACAd.setRowCount(0);
            Object[] objeto = new Object[5];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getIdClienteVD();
                objeto[1] = lista.get(i).getIdProductoVD();
                objeto[2] = lista.get(i).getCantidadVD();
                objeto[3] = lista.get(i).getNumeroVentasVD();
                objeto[4] = lista.get(i).getTipoPagoVD();
                modeloventasACAd.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void agregarVentaDiaria() {
        try {
            int idClienteVD = Integer.parseInt(interfazAdmin.txtIDCliVenDiar.getText());
            int idProductoVD = Integer.parseInt(interfazAdmin.txtIDProdVenDiar.getText());
            int cantidadVD = Integer.parseInt(interfazAdmin.txtCantidadVenDiar.getText());
            int numeroVentasVD = Integer.parseInt(interfazAdmin.txtNumVenDiar.getText());
            String tipoPagoVD = interfazAdmin.txtTipoPagVenDiar.getText();

            v.setIdClienteVD(idClienteVD);
            v.setIdProductoVD(idProductoVD);
            v.setCantidadVD(cantidadVD);
            v.setNumeroVentasVD(numeroVentasVD);
            v.setTipoPagoVD(tipoPagoVD);

            if (cantidadVD == 0) {
                JOptionPane.showMessageDialog(interfazAdmin, "Error.");
            } else {
                int r = ventasACAd.agregar(v);
                if (r == 1) {
                    JOptionPane.showMessageDialog(interfazAdmin, "Venta Diaria Agregada!");
                } else {
                    JOptionPane.showMessageDialog(interfazAdmin, "No se pudo agregar la Venta Diaria.");
                }
                limpiarTablaVentasDiarias();
            }
        } catch (Exception e) {
        }
    }

    public void guardarVentaDiaria() {
        if (interfazAdmin.txtIDCliVenDiar.getText().equals("")) {
            JOptionPane.showMessageDialog(interfazAdmin, "No se identifica ID de Cliente...");
        } else {
            try {
                int idCliente = Integer.parseInt(interfazAdmin.txtIDCliVenDiar.getText());
                int idProducto = Integer.parseInt(interfazAdmin.txtIDProdVenDiar.getText());
                int cantidad = Integer.parseInt(interfazAdmin.txtCantidadVenDiar.getText());
                int numeroVentas = Integer.parseInt(interfazAdmin.txtNumVenDiar.getText());
                String tipoPago = interfazAdmin.txtTipoPagVenDiar.getText();

                v.setIdClienteVD(idCliente);
                v.setIdProductoVD(idProducto);
                v.setCantidadVD(cantidad);
                v.setNumeroVentasVD(numeroVentas);
                v.setTipoPagoVD(tipoPago);

                if (idCliente < 0 || idProducto < 0 || cantidad < 0 || numeroVentas < 0 || tipoPago.equals("")) {
                    JOptionPane.showMessageDialog(interfazAdmin, "Error, campos vacíos o con valores no válidos");
                } else {
                    int r = ventasACAd.modificar(v);
                    if (r == 1) {
                        JOptionPane.showMessageDialog(interfazAdmin, "Venta diaria actualizada.");
                        limpiarTablaVentasDiarias();
                        consultarVentasDiarias(interfazAdmin.jTableVenDiar);
                        limpiarVentasDiarias();
                    } else {
                        JOptionPane.showMessageDialog(interfazAdmin, "Error al actualizar la venta diaria.");
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(interfazAdmin, "Error: Verifique que todos los campos numéricos sean correctos.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(interfazAdmin, "Error: " + e.getMessage());
            }
        }
    }

    public void eliminarVentaDiaria() {
        int fila = interfazAdmin.jTableVenDiar.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(interfazAdmin, "Debes seleccionar una fila.");
        } else {
            int idClienteVD = Integer.parseInt(interfazAdmin.jTableVenDiar.getValueAt(fila, 0).toString());
            ventasACAd.eliminar(idClienteVD);
            System.err.println("Venta Diaria Eliminada con ID Cliente " + idClienteVD);
            JOptionPane.showMessageDialog(interfazAdmin, "Venta Diaria con ID Cliente " + idClienteVD + " Eliminada.");
        }
        limpiarTablaProducto();
    }

    public void modificarVentaDiaria() {
        int fila = interfazAdmin.jTableVenDiar.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(interfazAdmin, "Debe Seleccionar una fila");
        } else {
            int idClienteVD = Integer.parseInt(interfazAdmin.jTableVenDiar.getValueAt(fila, 0).toString());
            int idProductoVD = Integer.parseInt(interfazAdmin.jTableVenDiar.getValueAt(fila, 1).toString());
            int cantidadVD = Integer.parseInt(interfazAdmin.jTableVenDiar.getValueAt(fila, 2).toString());
            int numeroVentasVD = Integer.parseInt(interfazAdmin.jTableVenDiar.getValueAt(fila, 3).toString());
            String tipoPagoVD = interfazAdmin.jTableVenDiar.getValueAt(fila, 4).toString();

            interfazAdmin.txtIDCliVenDiar.setText(String.valueOf(idClienteVD));
            interfazAdmin.txtIDProdVenDiar.setText(String.valueOf(idProductoVD));
            interfazAdmin.txtCantidadVenDiar.setText(String.valueOf(cantidadVD));
            interfazAdmin.txtNumVenDiar.setText(String.valueOf(numeroVentasVD));
            interfazAdmin.txtTipoPagVenDiar.setText(tipoPagoVD);
        }
    }
    
    // Metodos Principales ClientesAdmin
    
    //Metodos principales clientes admin
    public void consultarCliente(JTable tabla) {
        modeloclienteACAd = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modeloclienteACAd);
        try {
            List<Cliente> lista = clienteACAd.consultarCliente();
            modeloclienteACAd.setRowCount(0);
            Object[] objeto = new Object[6];
            for (int i = 0; i < lista.size(); i++) {
                objeto[0] = lista.get(i).getIdCliente();
                objeto[1] = lista.get(i).getRutCliente();
                objeto[2] = lista.get(i).getNombreCliente();
                objeto[3] = lista.get(i).getApellidoCliente();
                objeto[4] = lista.get(i).getEmailCliente();
                objeto[5] = lista.get(i).getCelularCliente();
                modeloclienteACAd.addRow(objeto);
            }
            tabla.setRowHeight(35);
            tabla.setRowMargin(10);
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public void agregarCliente() {
        try {
            int idCliente = Integer.parseInt(interfazAdmin.txtIDCliente.getText());
            int rutCliente = Integer.parseInt(interfazAdmin.txtRUTCliente.getText());
            String nombreCliente = interfazAdmin.txtNombreCliente.getText();
            String apellidoCliente = interfazAdmin.txtApellidoCliente.getText();
            String emailCliente = interfazAdmin.txtEmailCliente.getText();
            String celularCliente = interfazAdmin.txtCelularCliente.getText();
            cl.setIdCliente(idCliente);
            cl.setRutCliente(rutCliente);
            cl.setNombreCliente(nombreCliente);
            cl.setApellidoCliente(apellidoCliente);
            cl.setEmailCliente(emailCliente);
            cl.setCelularCliente(celularCliente);

            if (rutCliente == 0) {
                JOptionPane.showMessageDialog(interfazAdmin, "Error.");
            } else {
                int rut = clienteACAd.agregarCliente(cl);
                if (rut == 1) {
                    JOptionPane.showMessageDialog(interfazAdmin, "Cliente Agregado!");
                } else {
                    JOptionPane.showMessageDialog(interfazAdmin, "No se pudo agregar al Cliente.");
                }
                limpiarTablaCliente();
            }
        } catch (Exception e) {
        }

    }

    public void guardarCliente() {
        if (interfazAdmin.txtIDCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(interfazAdmin, "No se identifica Nombre de Cliente...");
        } else {
            
            try {
                int idCliente = Integer.parseInt(interfazAdmin.txtIDCliente.getText());
                int rutCliente = Integer.parseInt(interfazAdmin.txtRUTCliente.getText());
                String nombreCliente = interfazAdmin.txtNombreCliente.getText();
                String apellidoCliente = interfazAdmin.txtApellidoCliente.getText();
                String emailCliente = interfazAdmin.txtEmailCliente.getText();
                String celularCliente = interfazAdmin.txtCelularCliente.getText();
                cl.setIdCliente(idCliente);
                cl.setRutCliente(rutCliente);
                cl.setNombreCliente(nombreCliente);;
                cl.setApellidoCliente(apellidoCliente);
                cl.setEmailCliente(emailCliente);
                cl.setCelularCliente(celularCliente);

                if (idCliente < 0 || rutCliente < 0 || nombreCliente.equals("") || apellidoCliente.equals("") || emailCliente.equals("") || celularCliente.equals("")) {
                    JOptionPane.showMessageDialog(interfazAdmin, "Error, campos vacíos o con valores no válidos");
                } else {
                    int r = clienteACAd.modificarCliente(cl);
                    if (r == 1) {
                        JOptionPane.showMessageDialog(interfazAdmin, "Cliente actualizado.");
                        limpiarTablaCliente();
                        consultarCliente(interfazAdmin.jTableCliente);
                        limpiarCliente();
                    } else {
                        JOptionPane.showMessageDialog(interfazAdmin, "Error al actualizar el Cliente.");
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(interfazAdmin, "Error: Verifique que todos los campos numéricos sean correctos.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(interfazAdmin, "Error: " + e.getMessage());
            }
        }
    }

    public void eliminarCliente() {
        int fila = interfazAdmin.jTableCliente.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(interfazAdmin, "Debes seleccionar una fila.");
        } else {
            int idCliente = Integer.parseInt(interfazAdmin.jTableCliente.getValueAt(fila, 0).toString());
            clienteACAd.eliminarCliente(idCliente);
            System.err.println("Cliente Eliminado con rut " + idCliente);
            JOptionPane.showMessageDialog(interfazAdmin, "Cliente con ID " + idCliente + " Eliminado.");
        }
        limpiarTablaCliente();
    }

    public void modificarCliente() {
        int fila = interfazAdmin.jTableCliente.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(interfazAdmin, "Debe Seleccionar una fila");
        } else {
            int idCliente = Integer.parseInt(interfazAdmin.jTableCliente.getValueAt(fila, 0).toString());
            int rutCliente = Integer.parseInt(interfazAdmin.jTableCliente.getValueAt(fila, 1).toString());
            String nombreCliente = interfazAdmin.jTableCliente.getValueAt(fila, 2).toString();
            String apellidoCliente = interfazAdmin.jTableCliente.getValueAt(fila, 3).toString();
            String emailCliente = interfazAdmin.jTableCliente.getValueAt(fila, 4).toString();
            String celularCliente = interfazAdmin.jTableCliente.getValueAt(fila, 5).toString();
            interfazAdmin.txtIDCliente.setText(String.valueOf(idCliente));
            interfazAdmin.txtRUTCliente.setText(String.valueOf(rutCliente));
            interfazAdmin.txtNombreCliente.setText(nombreCliente);
            interfazAdmin.txtApellidoCliente.setText(apellidoCliente);
            interfazAdmin.txtEmailCliente.setText(emailCliente);
            interfazAdmin.txtCelularCliente.setText(celularCliente);
        }
    }

}


