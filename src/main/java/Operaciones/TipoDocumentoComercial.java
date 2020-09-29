package Operaciones;

import Persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "tipoDocumentoComercial")
public class TipoDocumentoComercial extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;


    //-------------------------------------------------------------------------
                    //CONTRUCTOR
    //-------------------------------------------------------------------------

    public TipoDocumentoComercial() { }

    public TipoDocumentoComercial(String nombre) {
        this.nombre = nombre;
    }

    //-------------------------------------------------------------------------
                    //GETTERS
    //-------------------------------------------------------------------------


    public String getNombre() { return nombre; }
}
