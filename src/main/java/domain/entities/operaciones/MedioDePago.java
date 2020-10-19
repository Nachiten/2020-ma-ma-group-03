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
    private int numero;

    //-------------------------------------------------------------------------
                        //CONTRUCTOR
    //-------------------------------------------------------------------------

    public MedioDePago(){

    }

    public MedioDePago(TipoMedioDePago tipo, int numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

}