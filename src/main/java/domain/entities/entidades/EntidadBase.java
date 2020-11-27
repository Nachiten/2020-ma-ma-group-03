package domain.entities.entidades;

import persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "entidadBase")
public class EntidadBase extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nombreFicticio")
    private String nombreFicticio;

    @Column(name = "razonSocial")
    private String razonSocial;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "entidadJuridicaAsociada_id")
    private EntidadJuridica entidadJuridicaAsociada;

    public EntidadBase() {
    }

    public EntidadBase(String nombre,String nombreFicticio){
        this.nombre = nombre;
        this.nombreFicticio = nombreFicticio;

    }

    public void asociarEntidadJuridica(EntidadJuridica entidadJuridica){
        this.entidadJuridicaAsociada = entidadJuridica;
    }
}
