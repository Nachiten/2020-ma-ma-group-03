package miTest;
import ApiMercadoLibre.ListadoPaises;
import ApiMercadoLibre.Pais;
import ApiMercadoLibre.ServicioUbicacionMercadoLibre;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ApiMercadoLibreTest {

    @Test
    public void obtenerListaPaises() throws IOException {
        ServicioUbicacionMercadoLibre servicioPais = ServicioUbicacionMercadoLibre.instancia();
        List<Pais> listaDePaises = servicioPais.listadoDePaises();

        if (listaDePaises != null){
            for(Pais unPais: listaDePaises){
                System.out.println("Nombre: " + unPais.getName() + " | Locale: " + unPais.getLocale() + " | ID: " + unPais.getId());

                //String name;
                //String locale;
            }
        } else {
            System.out.println("No se pudo leer la lista de paises");
        }

    }

}
