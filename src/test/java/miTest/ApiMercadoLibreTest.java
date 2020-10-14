package miTest;
import ApiMercadoLibre.*;
import Excepciones.ExcepcionApiMercadoLibre;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ApiMercadoLibreTest {

    @Test
    public void obtenerListaPaises() throws IOException, ExcepcionApiMercadoLibre {

        List<Pais> listaDePaises = ApiMercadoLibreInfo.getPaises();

       if (listaDePaises != null){
            for(Pais unPais: listaDePaises){
                System.out.println("***********************************************************");
                System.out.println("Nombre pais: " + unPais.getName() + " | Locale: " + unPais.getLocale() + " | ID: " + unPais.getId()
                                    + " |  Currency_id: " + unPais.getCurrency_id());

               /*  ApiMercadoLibreInfo apiMercadoLibreInfo = new ApiMercadoLibreInfo();
                apiMercadoLibreInfo.cargarMonedaPorPais(unPais.getId());
               System.out.println("-------- Moneda descripcion: "+ unPais.getCurrency_id().getDescripcion()
                        + " ID: " + unPais.getCurrency_id().getId()
                        + " Simbolo: "+ unPais.getCurrency_id().getSymbol()+ "---------");*/

                InfoPais provinciasDePais = ApiMercadoLibreInfo.obtenerProvinciasDePais(unPais.getId());
                System.out.println("NOMBRE DE PAIS:"+ provinciasDePais.getName());
                for(InfoEstado unEstado : provinciasDePais.getStates()){
                    System.out.println("**Nombre provincia: " + unEstado.getName() + " | ID: " + unEstado.getId());

                    InfoEstado ciudadesDeProvincias = ApiMercadoLibreInfo.obtenerCiudadesDeEstado(unEstado.getId());
                    for (InfoCiudad ciudad :ciudadesDeProvincias.getCities()) {
                        System.out.println("****Nombre ciudad: " +ciudad.getName()+ " | ID: " +ciudad.getId());

                    }
                }
            }
        } else {
            System.out.println("No se pudo leer la lista de paises");
        }

    }

    @Test
    public void obtenerProvinciasDeArg() throws IOException, ExcepcionApiMercadoLibre {

        /*InfoPais provinciasDeArg = ApiMercadoLibreInfo.obtenerProvinciasDePais("AR");

        for(Estado unEstado : provinciasDeArg.getStates()){
            System.out.println("Nombre: " + unEstado.getName() + " | ID: " + unEstado.getId());
        }*/
    }


    @Test
    // No funciona :P
    public void obtenerCiudadesDeEstadoRocha() throws IOException, ExcepcionApiMercadoLibre{

        // Deberia funcionar de esta manera pero no funciona
        // Este metodo lee los datos de la clase ApiMecardoLibreInfo que tiene todos los datos cargados
        // ( se cargan en el metodo setupInicialApi() )
        //setupInicialApi();
        //InfoEstado estados = datosApi.obtenerCiudadesDeEstado("UY-RO");

        // Obtener los datos en el momento (si funciona)
        ServicioUbicacionMercadoLibre servicioCiudades = ServicioUbicacionMercadoLibre.instancia();
        InfoEstado estados = servicioCiudades.listadoCiudadesDeEstado("UY-RO");

        if (estados.getCities() != null){
            for(InfoCiudad unaCiudad : estados.getCities()){
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
    public void crearDirrecionPostal() throws IOException, ExcepcionApiMercadoLibre {
        DireccionPostal miDireccion = new DireccionPostal();

        Direccion direccionCasa = new Direccion("Malabia", 3,20, "F");

        miDireccion.configurarDireccionPostal("Argentina", "Córdoba", "La Carlota", direccionCasa);

        Assert.assertEquals("La Carlota", miDireccion.getCiudad().getName());
        Assert.assertEquals("Argentina", miDireccion.getPais().getName());
        Assert.assertEquals("Córdoba", miDireccion.getProvincia().getName());
    }
}
