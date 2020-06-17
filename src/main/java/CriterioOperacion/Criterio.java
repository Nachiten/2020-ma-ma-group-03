package CriterioOperacion;

import java.util.List;

public class Criterio {

    private List<CategoriaCriterio> listaCategoriaCriterio;
    private Criterio criterio;

    public void agregarCategoria(CategoriaCriterio categoriaCriterio){
        listaCategoriaCriterio.add(categoriaCriterio);
    }

    public void tenerMasJerarquiaQue(Criterio criterio){
        setCriterio(criterio);
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }
}
