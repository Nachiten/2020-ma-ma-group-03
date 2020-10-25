package criterioOperacion;

import persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "categoriacriterio")
public class CategoriaCriterio extends EntidadPersistente {
    @Column (name = "nombre")
    private String nombre;
    @Column (name = "descripcion")
    private String descripcion;

    public CategoriaCriterio(){

    }

    public CategoriaCriterio(String nombre, String descrpcion){
        this.nombre = nombre;
        this.descripcion = descrpcion;
    }
}