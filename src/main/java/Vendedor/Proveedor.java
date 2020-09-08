package Vendedor;

import ApiMercadoLibre.DireccionPostal;

public class Proveedor {
    private String nombre;
    private String apellido;
    private int dni;
    private DireccionPostal direccionPostal;
    private String razonSocial;
    private int cuit;

    public Proveedor(String nombre, String apellido, int dni, DireccionPostal direccionPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccionPostal = direccionPostal;
    }

    public Proveedor(String razonSocial, int cuit, DireccionPostal direccionPostal) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.direccionPostal = direccionPostal;
    }
}