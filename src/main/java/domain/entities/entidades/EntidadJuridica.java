package domain.entities.entidades;

import persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entidadJuridica")
public class EntidadJuridica extends EntidadPersistente {

    @Column(unique = true, name = "nombreEntidadJuridica")
    private String nombreEntidadJuridica;

    @OneToMany(mappedBy = "entidadJuridicaAsociada", cascade = {CascadeType.ALL})
    private  List<Entidad> entidadesAsociadas;

    @OneToMany(mappedBy = "entidadJuridicaAsociada", cascade = {CascadeType.ALL})
    private List<EntidadBase> entidadesBase;

    @Column(name = "estoyHabilitado")
    private boolean estoyHabilitado;

    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------


    public EntidadJuridica() {
        inicializar();
    }

    public EntidadJuridica(String nombreEntidadJuridica) {
        this.nombreEntidadJuridica = nombreEntidadJuridica;
        inicializar();
    }

    //-------------------------------------------------------------------------
                            //METODOS
    //-------------------------------------------------------------------------

    private void inicializar(){
        this.estoyHabilitado = true;
        this.entidadesAsociadas = new ArrayList<>();
        this.entidadesBase = new ArrayList<>();
    }
    public void cambiarAHabilitado(){
        this.estoyHabilitado = true;
    }

    public void cambiarAInhabilitado(){
        this.estoyHabilitado = false;
    }
    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------

    public String getNombreEntidadJuridica() {
        return nombreEntidadJuridica;
    }

    public List<EntidadBase> getEntidadesBase() {
        return entidadesBase;
    }

    public List<Entidad> getEntidadesAsociadas() {
        return entidadesAsociadas;
    }

    public boolean estoyHabilitado() {
        return estoyHabilitado;
    }

    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------

    public void setNombreEntidadJuridica(String nombreEntidadJuridica) {
        this.nombreEntidadJuridica = nombreEntidadJuridica;
    }

    public void setEntidadesBase(List<EntidadBase> entidadesBase) {
        this.entidadesBase = entidadesBase;
    }

    public void setEntidadesAsociadas(List<Entidad> entidadesAsociadas) {
        this.entidadesAsociadas = entidadesAsociadas;
    }
}
