package domain.entities.vendedor;

import domain.entities.apiMercadoLibre.DireccionPostal;
import persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table (name = "proveedor")
public class Proveedor extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombre;
    @Column (name = "apellido")
    private String apellido;
    @Column(name = "dni")
    private int dni;
    @OneToOne
    @JoinColumn(name = "direccionPostal_id", referencedColumnName = "id")
    private DireccionPostal direccionPostal;
    @Column(name = "razonSocial")
    private String razonSocial;
    @Column(name = "cuit")
    private int cuit;
    @Column(name="estoyHabilitado")
    private boolean estoyHabilitado ;

    public Proveedor(){
        inicializar();

    }

    public Proveedor(String nombre, String apellido, int dni, DireccionPostal direccionPostal, String razonSocial) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccionPostal = direccionPostal;
        this.razonSocial = razonSocial;
        inicializar();
    }

    public Proveedor(String razonSocial, int cuit, DireccionPostal direccionPostal) {
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.direccionPostal = direccionPostal;
        inicializar();
    }

    private void inicializar(){
        this.estoyHabilitado = true;
    }
    public void cambiarAHabilitado(){
        this.setEstoyHabilitado(true);
    }

    public void cambiarAInhabilitado(){
        this.setEstoyHabilitado(false);
    }

    //-------------------------------------------------------------------------
                                    //GETTERS
    //-------------------------------------------------------------------------

    public String getRazonSocial() {
        return razonSocial;
    }

    public boolean getEstoyHabilitado(){
        return estoyHabilitado;
    }

    //-------------------------------------------------------------------------
                                  //SETTERS
    //-------------------------------------------------------------------------

    public void setEstoyHabilitado(Boolean estoyHabilitado){
        this.estoyHabilitado= estoyHabilitado;
    }
}