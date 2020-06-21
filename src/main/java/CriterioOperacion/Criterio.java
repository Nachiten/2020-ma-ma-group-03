package CriterioOperacion;

import java.util.List;

public class Criterio {

    private List<CategoriaCriterio> listaCategoriaCriterio;
    private Criterio criterio;
    private String nombre;

    public void agregarCategoria(CategoriaCriterio categoriaCriterio){
        listaCategoriaCriterio.add(categoriaCriterio);
    }

    public void tenerMasJerarquiaQue(CategoriaCriterio categoriaCriterio){
        agregarPadre(criterio);
    }

    public void agregarPadre(Criterio criterio) {
        this.criterio = criterio;
    }
}