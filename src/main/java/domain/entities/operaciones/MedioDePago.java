package domain.entities.operaciones;

import persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table (name = "medioDePago")
public class MedioDePago extends EntidadPersistente {

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "tipoMedioPago_id", referencedColumnName = "id")
    private TipoMedioDePago tipo;
    @Column (name = "numero")
    private String numero;

    //-------------------------------------------------------------------------
                        //CONTRUCTOR
    //-------------------------------------------------------------------------

    public MedioDePago(){

    }

    public MedioDePago(TipoMedioDePago tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public MedioDePago(TipoMedioDePago tipo) {
        this.tipo = tipo;

    }

    public TipoMedioDePago getTipo() {
        return tipo;
    }
}