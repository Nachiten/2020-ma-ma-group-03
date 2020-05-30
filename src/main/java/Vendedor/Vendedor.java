package Vendedor;

public class Vendedor {
    public String nombre;
    public String apellido;
    public int dni;
    public String direccionPostal;
    public String razonSocial;
    public int cuit;

    public Vendedor(String nombre, String apellido, int dni, String direccionPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccionPostal = direccionPostal;
    }

    public Vendedor(String razonSocial, int cuit, String direccionPostal) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.direccionPostal = direccionPostal;
    }
}