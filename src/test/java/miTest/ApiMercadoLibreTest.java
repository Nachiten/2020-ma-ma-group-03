package miTest;
import ApiMercadoLibre.*;
import org.junit.Test;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.util.List;

public class ApiMercadoLibreTest {

    @Test
    public void obtenerListaPaises() throws IOException {
        ServicioUbicacionMercadoLibre servicioPais = ServicioUbicacionMercadoLibre.instancia();
        // Aca se guarda la lista de todos los paises traidos de la API
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

    @Test
    public void obtenerProvinciasDeArg() throws IOException{
        ServicioUbicacionMercadoLibre servicioPais = ServicioUbicacionMercadoLibre.instancia();
        InfoPais provinciasDeArg = servicioPais.listadoEstadosDePais("AR");

        if (provinciasDeArg.getStates() != null){
            for(Estado unEstado: provinciasDeArg.getStates()){
                System.out.println("Nombre: " + unEstado.getName() + " | ID: " + unEstado.getId());

                //String name;
                //String locale;
            }
        } else {
            System.out.println("No se pudo leer la lista de paises");
        }

    }

}
