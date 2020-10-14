package CriterioOperacion;

import Persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "CategoriaCriterio")
public class CategoriaCriterio extends EntidadPersistente {
    @Column (name = "nombre")
    private String nombre;
    @Column (name = "descripcion")
    private String descripcion;
}