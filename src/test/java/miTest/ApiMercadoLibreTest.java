package miTest;
import ApiMercadoLibre.*;
import Excepciones.ExcepcionApiMercadoLibre;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ApiMercadoLibreTest {

    ApiMercadoLibreInfo datosApi = null;

    void setupInicialApi() throws IOException{
        if (datosApi == null){
            datosApi = new ApiMercadoLibreInfo();
            datosApi.obtenerDatosApiMercadoLibre();
        }
    }

    @Test
    public void obtenerListaPaises() throws IOException {

        setupInicialApi();

        List<Pais> listaDePaises = datosApi.getPaises();

        if (listaDePaises != null){
            for(Pais unPais: listaDePaises){
                System.out.println("Nombre: " + unPais.getName() + " | Locale: " + unPais.getLocale() + " | ID: " + unPais.getId());
            }
        } else {
            System.out.println("No se pudo leer la lista de paises");
        }

    }

    @Test
    public void obtenerProvinciasDeArg() throws IOException, ExcepcionApiMercadoLibre {
        setupInicialApi();

        InfoPais provinciasDeArg = datosApi.obtenerProvinciasDePais("AR");

        for(Estado unEstado : provinciasDeArg.getStates()){
            System.out.println("Nombre: " + unEstado.getName() + " | ID: " + unEstado.getId());
        }
    }

    @Test
    public void obtenerCiudadesDeEstadoRocha() throws IOException{
        ServicioUbicacionMercadoLibre servicioPais = ServicioUbicacionMercadoLibre.instancia();
        InfoEstado estados = servicioPais.listadoCiudadesDeEstado("UY-RO");

        if (estados.getCities() != null){
            for(Ciudad unaCiudad : estados.getCities()){
                System.out.println("Nombre: " + unaCiudad.getName() + " | ID: " + unaCiudad.getId());
            }
        } else {
            System.out.println("No se pudo leer la lista de paises");
        }

        Assert.assertNotNull(estados.getCities());
    }

    @Test
    public void obtenerMonedas() throws IOException{
        ServicioUbicacionMercadoLibre servicioMonedas = ServicioUbicacionMercadoLibre.instancia();
        List<InfoMoneda> monedas = servicioMonedas.listadoMonedas();

        if ( monedas != null){
            for(InfoMoneda infoMoneda: monedas){
                System.out.println("ID: " + infoMoneda.getId() + "| Simbolo: " + infoMoneda.getSymbol() + " | Descripcion: " + infoMoneda.getDescripcion());
            }
        } else {
            System.out.println("No se pudo leer la lista de monedas");
        }

        Assert.assertNotNull(monedas);

    }

    @Test
    public void crearDirrecionPostal() throws IOException{
        DireccionPostal miDireccion = new DireccionPostal();

        miDireccion.configurarDireccionPostal("Argentina", "Córdoba", "La Carlota");

        Assert.assertEquals("La Carlota", miDireccion.getCiudad().getName());
        Assert.assertEquals("Argentina", miDireccion.getPais().getName());
        Assert.assertEquals("Córdoba", miDireccion.getProvincia().getName());
    }
}
