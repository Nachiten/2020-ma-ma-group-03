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
        CategoriaCriterio categoria1 = new CategoriaCriterio("Oficina", "Articulos de oficina");
        CategoriaCriterio categoria2 = new CategoriaCriterio("Limpieza", "Articulos de limpieza");
        CategoriaCriterio categoria3 = new CategoriaCriterio("Seguridad", "Articulos de seguridad");

        List<CategoriaCriterio> categoriasCriterioInsumos = new ArrayList<>();
        categoriasCriterioInsumos.add(categoria1);
        categoriasCriterioInsumos.add(categoria2);
        categoriasCriterioInsumos.add(categoria3);

        Criterio miCriterio1 = new Criterio("Insumos", categoriasCriterioInsumos);


        CategoriaCriterio categoria4 = new CategoriaCriterio("Edilicio", "Cosas edilicias");
        CategoriaCriterio categoria5 = new CategoriaCriterio("Tecnico", "Reparacion");
        CategoriaCriterio categoria6 = new CategoriaCriterio("Software", "Computadoras");

        List<CategoriaCriterio> categoriasCriterioMantenimiento = new ArrayList<>();
        categoriasCriterioMantenimiento.add(categoria4);
        categoriasCriterioMantenimiento.add(categoria5);
        categoriasCriterioMantenimiento.add(categoria6);

        Criterio miCriterio2 = new Criterio("Mantenimiento", categoriasCriterioMantenimiento);


        CategoriaCriterio categoria7 = new CategoriaCriterio("Nacional", "Dentro del pais");
        CategoriaCriterio categoria8 = new CategoriaCriterio("Internacional", "Fuera del pais");
        CategoriaCriterio categoria9 = new CategoriaCriterio("Ambos", "Los dos anteriores");
        List<CategoriaCriterio> categoriasCriterioAlcance = new ArrayList<>();

        categoriasCriterioAlcance.add(categoria7);
        categoriasCriterioAlcance.add(categoria8);
        categoriasCriterioAlcance.add(categoria9);

        Criterio miCriterio3 = new Criterio("Alcance", categoriasCriterioAlcance);


        CategoriaCriterio categoria10 = new CategoriaCriterio("Caro", "Mayor a 1M");
        CategoriaCriterio categoria11 = new CategoriaCriterio("Intermedio", "Entre 100k y 1M");
        CategoriaCriterio categoria12 = new CategoriaCriterio("Barato", "Menor a 100k");
        List<CategoriaCriterio> categoriasCriterioValor = new ArrayList<>();

        categoriasCriterioValor.add(categoria10);
        categoriasCriterioValor.add(categoria11);
        categoriasCriterioValor.add(categoria12);

        Criterio miCriterio4 = new Criterio("Valor", categoriasCriterioValor);


        CategoriaCriterio categoria13 = new CategoriaCriterio("Interno", "Dentro de la empresa");
        CategoriaCriterio categoria14 = new CategoriaCriterio("Externo", "Fuera de la empresa");
        List<CategoriaCriterio> categoriasCriterioCliente = new ArrayList<>();

        categoriasCriterioCliente.add(categoria13);
        categoriasCriterioCliente.add(categoria14);

        Criterio miCriterio5 = new Criterio("Cliente", categoriasCriterioCliente);

        persistirUnObjeto(miCriterio1);
        persistirUnObjeto(miCriterio2);
        persistirUnObjeto(miCriterio3);
        persistirUnObjeto(miCriterio4);
        persistirUnObjeto(miCriterio5);

    }
}
