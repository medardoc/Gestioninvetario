package Modelo;

public class Proveedores {

    private int idProveedores;
    private String nombreProveedores;

    public Proveedores() {
    }

    public Proveedores(int idProveedores, String nombreProveedores) {
        this.idProveedores = idProveedores;
        this.nombreProveedores = nombreProveedores;
    }
    //mutadores GET y accesadores SET 
    public int getIdProveedores() {
        return idProveedores;
    }

    public void setIdProveedores(int idProveedores) {
        this.idProveedores = idProveedores;
    }

    public String getNombreProveedores() {
        return nombreProveedores;
    }

    public void setNombreProveedores(String nombreProveedores) {
        this.nombreProveedores = nombreProveedores;
    }

}
