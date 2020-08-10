package miTest;
import ApiMercadoLibre.ListadoPaises;
import ApiMercadoLibre.Pais;
import ApiMercadoLibre.ServicioPaisMercadoLibre;
import Entidades.Afip;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ApiMercadoLibreTest {

    @Test
    public void obtenerListaPaises() throws IOException {
        ServicioPaisMercadoLibre servicioPais = ServicioPaisMercadoLibre.instancia();
        ListadoPaises listaDePaises = servicioPais.listadoDePaises();

        for(Pais unPais: listaDePaises.paises){
            System.out.println("Nombre: " + unPais.getName() + "Locale: " + unPais.getLocale());

            //String name;
            //String locale;
        }
    }

}
