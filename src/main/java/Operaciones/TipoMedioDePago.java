package Operaciones;

import Persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "tipoMedioDePago")
public class TipoMedioDePago extends EntidadPersistente {
    @Column(name = "tipoPago")
    private String tipoPago;

    public TipoMedioDePago(){

    }

    public TipoMedioDePago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}
