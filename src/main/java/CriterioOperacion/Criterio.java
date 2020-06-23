package CriterioOperacion;

import java.util.List;

public class Criterio {

    private List<CategoriaCriterio> listaCategoriaCriterio;
    private Criterio criterioPadre;
    private String nombre;

    public void agregarCategoria(CategoriaCriterio categoriaCriterio){
        listaCategoriaCriterio.add(categoriaCriterio);
    }

    public void tenerMasJerarquiaQue(Criterio unCriterio){
       unCriterio.agregarPadre(this);
    }

    public void agregarPadre(Criterio criterio) {
        this.criterioPadre = criterio;
    }
}