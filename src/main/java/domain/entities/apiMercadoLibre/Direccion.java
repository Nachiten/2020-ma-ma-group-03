package domain.entities.apiMercadoLibre;


import persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "direccion")
public class Direccion extends EntidadPersistente {

    @Column (name = "calle")
    private String calle;

    @Column (name = "numero")
    private int numero;

    @Column (name = "piso")
    private int piso;

    @Column (name = "dpto")
    private String dpto;

    @Column (name="barrio")
    private String barrio;
    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Direccion(){

    }

    public Direccion(String calle, int numero, int piso, String dpto, String barrio){
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.dpto = dpto;
        this.barrio = barrio;
    }

    public Direccion(String calle, int numero, int piso, String dpto){
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.dpto = dpto;

    }

    public Direccion(String calle, int numero) {
        this.calle = calle;
        this.numero = numero;
    }

    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------

    public int getNumero() {
        return numero;
    }

    public int getPiso() {
        return piso;
    }

    public String getCalle() {
        return calle;
    }

    public String getDpto() {
        return dpto;
    }

    public String getBarrio() {
        return barrio;
    }

    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
