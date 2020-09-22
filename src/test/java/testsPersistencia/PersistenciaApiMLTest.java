package testsPersistencia;

import ApiMercadoLibre.ApiMercadoLibreInfo;
import ApiMercadoLibre.DireccionPostal;
import ApiMercadoLibre.Pais;
import Persistencia.db.EntityManagerHelper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class PersistenciaApiMLTest {

    static private ApiMercadoLibreInfo datosApi = null;
    static private DireccionPostal direccionPostal;
    static private List<Pais> listaDePaises;

    @BeforeClass
    public static void setupInicialApi() throws IOException {
        if (datosApi == null){
            datosApi = new ApiMercadoLibreInfo();
            datosApi.obtenerDatosApiMercadoLibre();
        }
        direccionPostal = new DireccionPostal();
        listaDePaises = new ArrayList<>();

    }

    private void persistirUnObjeto(Object unObjeto){
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(unObjeto);

        EntityManagerHelper.commit();
    }

    @Test
    public void persistirPaises(){

        listaDePaises = datosApi.getPaises();
        persistirUnObjeto(listaDePaises);
    }
}
