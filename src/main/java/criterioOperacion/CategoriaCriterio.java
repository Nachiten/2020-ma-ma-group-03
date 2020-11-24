package criterioOperacion;

import persistencia.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "categoriacriterio")
public class CategoriaCriterio extends EntidadPersistente {
    @Column (name = "nombreCategoria")
    private String nombreCategoria;
    @Column (name = "descripcion")
    private String descripcion;

    public CategoriaCriterio(){

    }

    public CategoriaCriterio(String nombre, String descrpcion){
        this.nombreCategoria = nombre;
        this.descripcion = descrpcion;
    }
    public CategoriaCriterio(String nombre){
        this.nombreCategoria = nombre;

    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }
}