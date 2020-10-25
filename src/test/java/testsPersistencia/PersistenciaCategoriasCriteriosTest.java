package testsPersistencia;

import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
import org.junit.Test;
import persistencia.db.EntityManagerHelper;

import java.util.ArrayList;
import java.util.List;

public class PersistenciaCategoriasCriteriosTest {
    private void persistirUnObjeto(Object unObjeto){

        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(unObjeto);

        EntityManagerHelper.commit();
    }

    @Test
    public void persistirCategoriasYCriterios(){
        // Criterio: Insumos, Mantenimiento

        // Insumos: Oficina, limpieza

        // Mantenimiento: Edilicio, tecnico
        CategoriaCriterio categoria1 = new CategoriaCriterio("Oficina", "Articulos de oficina");
        CategoriaCriterio categoria2 = new CategoriaCriterio("Limpieza", "Articulos de limpieza");
        CategoriaCriterio categoria3 = new CategoriaCriterio("Edilicio", "etc");
        CategoriaCriterio categoria4 = new CategoriaCriterio("Tecnico", "descripcion2");

        List<CategoriaCriterio> categoriasCriterioInsumos = new ArrayList<>();
        List<CategoriaCriterio> categoriasCriterioMantenimiento = new ArrayList<>();

        categoriasCriterioInsumos.add(categoria1);
        categoriasCriterioInsumos.add(categoria2);

        categoriasCriterioMantenimiento.add(categoria3);
        categoriasCriterioMantenimiento.add(categoria4);

        Criterio miCriterio = new Criterio("Insumos", categoriasCriterioInsumos);
        Criterio miCriterio2 = new Criterio("Mantenimiento", categoriasCriterioMantenimiento);

        persistirUnObjeto(miCriterio);
        persistirUnObjeto(miCriterio2);

    }
}
