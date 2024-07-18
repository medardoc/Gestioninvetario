package Modelo;

public class VentasDiarias {
    private int idClienteVD;
    private int idProductoVD;
    private int cantidadVD;
    private int numeroVentasVD;
    private String tipoPagoVD;
    private String fechaVenta;
    
    
    public VentasDiarias() {
    }

    public VentasDiarias(int idClienteVD, int idProductoVD, int cantidadVD, int numeroVentasVD, String tipoPagoVD, String fechaVenta) {
        this.idClienteVD = idClienteVD;
        this.idProductoVD = idProductoVD;
        this.cantidadVD = cantidadVD;
        this.numeroVentasVD = numeroVentasVD;
        this.tipoPagoVD = tipoPagoVD;
        this.fechaVenta = fechaVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdClienteVD() {
        return idClienteVD;
    }

    public void setIdClienteVD(int idClienteVD) {
        this.idClienteVD = idClienteVD;
    }

    public int getIdProductoVD() {
        return idProductoVD;
    }

    public void setIdProductoVD(int idProductoVD) {
        this.idProductoVD = idProductoVD;
    }

    public int getCantidadVD() {
        return cantidadVD;
    }

    public void setCantidadVD(int cantidadVD) {
        this.cantidadVD = cantidadVD;
    }

    public int getNumeroVentasVD() {
        return numeroVentasVD;
    }

    public void setNumeroVentasVD(int numeroVentasVD) {
        this.numeroVentasVD = numeroVentasVD;
    }

    public String getTipoPagoVD() {
        return tipoPagoVD;
    }

    public void setTipoPagoVD(String tipoPagoVD) {
        this.tipoPagoVD = tipoPagoVD;
    }
}
