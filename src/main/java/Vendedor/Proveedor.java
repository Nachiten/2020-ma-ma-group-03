package Vendedor;

public class Proveedor {
    private String nombre;
    private String apellido;
    private int dni;
    private String direccionPostal;
    private String razonSocial;
    private  int cuit;

    public Proveedor(String nombre, String apellido, int dni, String direccionPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccionPostal = direccionPostal;
    }

    public Proveedor(String razonSocial, int cuit, String direccionPostal) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.direccionPostal = direccionPostal;
    }
}