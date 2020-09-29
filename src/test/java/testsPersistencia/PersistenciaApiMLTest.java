package testsPersistencia;

import ApiMercadoLibre.*;
import Excepciones.ExcepcionApiMercadoLibre;
import Persistencia.db.EntityManagerHelper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.*;

public class PersistenciaApiMLTest {

    /*static private List<Pais> paises;
    static private List<InfoPais> listaPaises;

    @BeforeClass
    public static void init(){
        paises = ApiMercadoLibreInfo.getPaises();
        listaPaises = ApiMercadoLibreInfo.getPaisConListadoProvincias();

    }*/

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

   /* @Test
    public void persistirApi(){
        for (InfoPais infoPais : listaPaises) {
            persistirUnObjeto(infoPais);
        }
    }*/

    @Test
    public void persistirDireccionPostal() throws IOException, ExcepcionApiMercadoLibre {

        DireccionPostal unaDireccionPostal = new DireccionPostal();


        Direccion direccionCasa = new Direccion("Malabia", 3,20, "F");

        unaDireccionPostal.configurarDireccionPostal("Argentina", "Córdoba", "La Carlota", direccionCasa);

        persistirUnObjeto(unaDireccionPostal);
    }

    @Test
    public void persistirDatosApiMercaboLibre() throws ExcepcionApiMercadoLibre {

        List<Pais> listaDePaises = ApiMercadoLibreInfo.getPaises();

        if (listaDePaises != null){
            for(Pais unPais: listaDePaises){
                //InfoPais provinciasDePais = (InfoPais) ApiMercadoLibreInfo.getPaises();
                InfoPais provinciasDePais = ApiMercadoLibreInfo.obtenerProvinciasDePais(unPais.getId());

                for(InfoEstado unEstado : provinciasDePais.getStates()){
                    InfoEstado ciudadesDeProvincias = ApiMercadoLibreInfo.obtenerCiudadesDeEstado(unEstado.getId());

                    for (InfoCiudad ciudad :ciudadesDeProvincias.getCities()) {

                        persistirUnObjeto(provinciasDePais);
                        persistirUnObjeto(ciudadesDeProvincias);
                        persistirUnObjeto(ciudad);

                    }
                }
            }
        }

    }
}
