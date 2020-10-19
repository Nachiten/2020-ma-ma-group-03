package domain.entities.operaciones;

import persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "tipoMedioDePago")
public class TipoMedioDePago extends EntidadPersistente {
    @Column(name = "tipoPago")
    private String tipoPago;

    //-------------------------------------------------------------------------
                        //CONTRUCTOR
    //-------------------------------------------------------------------------

    public TipoMedioDePago(){  }

    public TipoMedioDePago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    //-------------------------------------------------------------------------
                        //GETTERS
    //-------------------------------------------------------------------------


    public String getTipoPago() { return tipoPago; }
}
