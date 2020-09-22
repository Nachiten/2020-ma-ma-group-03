package testsPersistencia;

import ApiMercadoLibre.*;
import Excepciones.ExcepcionApiMercadoLibre;
import Persistencia.db.EntityManagerHelper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class PersistenciaApiMLTest {

    private void persistirUnaLista(List<Object> miLista){
        for(Object unObjeto : miLista){
            persistirUnObjeto(unObjeto);
        }
    }

    private void persistirUnObjeto(Object unObjeto){
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(unObjeto);

        EntityManagerHelper.commit();
    }

    @Test
    public void persistirDireccionPostal() throws IOException, ExcepcionApiMercadoLibre {

        DireccionPostal unaDireccionPostal = new DireccionPostal();

        Direccion direccionCasa = new Direccion("Malabia", 3,20, "F");

        unaDireccionPostal.configurarDireccionPostal("Argentina", "Córdoba", "La Carlota", direccionCasa);

        persistirUnObjeto(unaDireccionPostal);
    }

    @Test
    public void persistirDatosApiMercaboLibre(){
        List<Pais> paises = ApiMercadoLibreInfo.getPaises();
        List<InfoEstado> provincias = ApiMercadoLibreInfo.getCiudades();

        persistirUnaLista(Collections.singletonList(paises));
        // Persistir el estado persiste tambien la lista de ciudades
        persistirUnaLista(Collections.singletonList(provincias));
    }
}
