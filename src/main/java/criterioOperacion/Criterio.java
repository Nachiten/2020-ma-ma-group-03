package criterioOperacion;

import persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "criterio")
public class Criterio extends EntidadPersistente {

    @OneToMany (cascade = CascadeType.ALL)
    private List<CategoriaCriterio> listaCategoriaCriterio;

    @ManyToOne (cascade = CascadeType.ALL)
    private Criterio criterioPadre;

    @Column (name = "nombre")
    private String nombre;

    public Criterio(){

    }

    public Criterio(String nombre, List<CategoriaCriterio> categorias ){
        listaCategoriaCriterio = new ArrayList<>();
        this.nombre = nombre;
        this.listaCategoriaCriterio = categorias;
    }

    public void agregarCategoria(CategoriaCriterio categoriaCriterio){
        listaCategoriaCriterio.add(categoriaCriterio);
    }

    public void tenerMasJerarquiaQue(Criterio unCriterio){
       unCriterio.agregarPadre(this);
    }

    public void agregarPadre(Criterio criterio) {
        this.criterioPadre = criterio;
    }

    public String getNombre() {
        return nombre;
    }

    public List<CategoriaCriterio> getListaCategoriaCriterio() {
        return listaCategoriaCriterio;
    }
}