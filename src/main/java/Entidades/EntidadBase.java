package Entidades;

import Persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "entidadBase")
public class EntidadBase extends EntidadPersistente {

    @Column(name = "nombreFicticio")
    private String nombreFicticio;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "entidadJuridicaAsociada_id")
    private EntidadJuridica entidadJuridicaAsociada;

    public EntidadBase() {
    }

    public EntidadBase(String nombreFicticio, String descripcion) {
        this.nombreFicticio = nombreFicticio;
        this.descripcion = descripcion;
    }
}
