package testsPersistencia;

import domain.entities.apiMercadoLibre.*;
import persistencia.db.EntityManagerHelper;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistenciaApiMLTest {

    static private List<Pais> listaDePaises;
    static private List<Estado> listaProvincias;
    static private List<Ciudad> listaCiudades;
    static private List<Monedas> listaMonedas;

    @BeforeClass
    public static void init(){
        listaDePaises = ApiMercadoLibreInfo.getListadoPaises();
        listaProvincias = ApiMercadoLibreInfo.getListadoProvincias();
        listaCiudades = ApiMercadoLibreInfo.getListadoCiudades();
        listaMonedas = ApiMercadoLibreInfo.getMonedas();

    }

    private void persistirUnObjeto(Object unObjeto){
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(unObjeto);

        EntityManagerHelper.commit();
    }

    @Test
    public void t1_persitirMonedas(){
        for (Monedas unaMoneda : listaMonedas ) {
            persistirUnObjeto(unaMoneda);
        }
    }

    @Test
    public void t2_persistirApiPaises(){
        for (Pais unPais : listaDePaises) {
            persistirUnObjeto(unPais);
        }
    }

    @Test
    public void t3_persistirApiProvincias(){
        for (Estado unaProvincia : listaProvincias) {
            persistirUnObjeto(unaProvincia);
        }
    }

    @Test
    public void t4_persistirApiCiudades(){
        int n = 1;
        for (Ciudad unaCiudad : listaCiudades) {
            unaCiudad.setId(unaCiudad.getId() + n);
            n++;
            persistirUnObjeto(unaCiudad);
        }
    }
}
