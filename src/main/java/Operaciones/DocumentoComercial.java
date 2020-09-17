package Operaciones;

import Persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table (name = "documentoComercial")
public class DocumentoComercial extends EntidadPersistente {

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "tipoDocumentoComercial_id", referencedColumnName = "id")
    private TipoDocumentoComercial tipo;
    @Column(name = "numero")
    private int numero;

    public DocumentoComercial(){

    }

    public DocumentoComercial(TipoDocumentoComercial tipo, int numero) {
        this.tipo = tipo;
        this.numero = numero;
    }
}