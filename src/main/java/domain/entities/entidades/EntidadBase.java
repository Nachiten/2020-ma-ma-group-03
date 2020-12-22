package domain.entities.entidades;

import persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "entidadBase")
public class EntidadBase extends EntidadPersistente {


    @Column(name = "nombreFicticio")
    private String nombreFicticio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "entidadJuridicaAsociada_id")
    private EntidadJuridica entidadJuridicaAsociada;

    @Column(name = "estoyHabilitado")
    private boolean estoyHabilitado;

    public EntidadBase() {
        inicializar();
    }

    public EntidadBase(String nombreFicticio){
        this.nombreFicticio = nombreFicticio;
        inicializar();
    }

    private void inicializar(){
        this.estoyHabilitado = true;
    }
    public void asociarEntidadJuridica(EntidadJuridica entidad){
        this.entidadJuridicaAsociada = entidad;
    }

    public void cambiarAHabilitado(){
        this.estoyHabilitado = true;
    }

    public void cambiarAInhabilitado(){
        this.estoyHabilitado = false;
    }

    public String getNombreFicticio() {
        return nombreFicticio;
    }

    public boolean getEstoyHabilitado() {
        return estoyHabilitado;
    }
    public EntidadJuridica getEntidadJuridicaAsociada() {
        return entidadJuridicaAsociada;
    }

    public void setEntidadJuridicaAsociada(EntidadJuridica entidadJuridicaAsociada) {
        this.entidadJuridicaAsociada = entidadJuridicaAsociada;
    }

    public void setNombreFicticio(String nombreFicticio) {
        this.nombreFicticio = nombreFicticio;
    }

    public void setEstoyHabilitado(boolean estoyHabilitado) {
        this.estoyHabilitado = estoyHabilitado;
    }
}
