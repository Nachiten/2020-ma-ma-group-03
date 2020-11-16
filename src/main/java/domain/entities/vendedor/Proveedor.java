package domain.entities.vendedor;

import domain.entities.apiMercadoLibre.DireccionPostal;
import persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table (name = "proveedor")
public class Proveedor extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombreProveedor;
    @Column (name = "apellido")
    private String apellidoProveedor;
    @Column(name = "dni")
    private int dniProveedor;
    @OneToOne
    @JoinColumn(name = "direccionPostal_id", referencedColumnName = "id")
    private DireccionPostal direccionPostal;
    @Column(name = "razonSocial")
    private String razonSocialProveedor;
    @Column(name = "cuit")
    private int cuit;
    @Column(name="estoyHabilitado")
    private boolean estoyHabilitado ;

    public Proveedor(){
        inicializar();

    }

    public Proveedor(String nombreProveedor, String apellidoProveedor, int dniProveedor, DireccionPostal direccionPostal, String razonSocialProveedor) {
        this.nombreProveedor = nombreProveedor;
        this.apellidoProveedor = apellidoProveedor;
        this.dniProveedor = dniProveedor;
        this.direccionPostal = direccionPostal;
        this.razonSocialProveedor = razonSocialProveedor;
        inicializar();
    }

    public Proveedor(String razonSocialProveedor, int cuit, DireccionPostal direccionPostal) {
        this.razonSocialProveedor = razonSocialProveedor;
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

    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    public boolean getEstoyHabilitado(){
        return estoyHabilitado;
    }

    public int getCuit() {
        return cuit;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public int getDniProveedor() {
        return dniProveedor;
    }

    public String getApellidoProveedor() {
        return apellidoProveedor;
    }

    //-------------------------------------------------------------------------
                                  //SETTERS
    //-------------------------------------------------------------------------

    public void setEstoyHabilitado(Boolean estoyHabilitado){
        this.estoyHabilitado= estoyHabilitado;
    }


}