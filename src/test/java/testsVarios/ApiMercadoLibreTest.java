package testsVarios;
import domain.entities.apiMercadoLibre.*;
import excepciones.ExcepcionApiMercadoLibre;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ApiMercadoLibreTest {

    @Test
    public void obtenerListaPaises() throws IOException, ExcepcionApiMercadoLibre {

        List<Pais> listaDeIdPaises = ApiMercadoLibreInfo.getListadoPaises();

        List<Moneda> listadoMonedas =  ApiMercadoLibreInfo.getMonedas();

       if (listaDeIdPaises != null || !listaDeIdPaises.isEmpty()){
            for(Pais unPais: listaDeIdPaises){

                System.out.println("***********************************************************");
                System.out.println("Nombre pais: " + unPais.getName() + " | Locale: " + unPais.getLocale() + " | ID: " + unPais.getId()
                                    + " |  Currency_id: "+ unPais.getCurrency_id());
                Optional resultado = listadoMonedas.stream().filter(monedas -> monedas.getId().equals(unPais.getCurrency_id())).findFirst();

                if(!resultado.isPresent()){
                    System.out.println("Para el pais: " + unPais.getName()+" no tiene informacion sobre su moneda ");
                }else {
                    Moneda moneda = (Moneda) resultado.get();
                    System.out.println("nombre moneda: " + moneda.getDescription());}

/*
                Pais provinciasDePais = ApiMercadoLibreInfo.obtenerProvinciasDePais(unPais.getId());
                System.out.println("NOMBRE DE PAIS:"+ provinciasDePais.getName());
                for(Estado unEstado : provinciasDePais.getStates()){
                    System.out.println("**Nombre provincia: " + unEstado.getName() + " | ID: " + unEstado.getId());

                    Estado ciudadesDeProvincias = ApiMercadoLibreInfo.obtenerCiudadesDeEstado(unEstado.getId());
                    for (Ciudad ciudad :ciudadesDeProvincias.getCities()) {
                        System.out.println("****Nombre ciudad: " +ciudad.getName()+ " | ID: " +ciudad.getId());

                    }
                }
            }
        } else {
            System.out.println("No se pudo leer la lista de idPaises");
        } */
    }
       }
    }

    @Test
    public void obtenerProvinciasDeArg() throws IOException, ExcepcionApiMercadoLibre {

        /*Pais provinciasDeArg = ApiMercadoLibreInfo.obtenerProvinciasDePais("AR");

        for(Estado unEstado : provinciasDeArg.getStates()){
            System.out.println("Nombre: " + unEstado.getName() + " | ID: " + unEstado.getId());
        }*/
    }


    @Test
    // No funciona :P
    public void obtenerCiudadesDeEstadoRocha() throws IOException, ExcepcionApiMercadoLibre{
/*
        // Deberia funcionar de esta manera pero no funciona
        // Este metodo lee los datos de la clase ApiMecardoLibreInfo que tiene todos los datos cargados
        // ( se cargan en el metodo setupInicialApi() )
        //setupInicialApi();
        //Estado estados = datosApi.obtenerCiudadesDeEstado("UY-RO");

        // Obtener los datos en el momento (si funciona)
        ServicioUbicacionMercadoLibre servicioCiudades = ServicioUbicacionMercadoLibre.instancia();
        Estado estados = servicioCiudades.obtenerUnEstado("UY-RO");

        if (estados.getCities() != null){
            for(Ciudad unaCiudad : estados.getCities()){
                System.out.println("Nombre: " + unaCiudad.getName() + " | ID: " + unaCiudad.getId());
            }
        } else {
            System.out.println("No se pudo leer la lista de idPaises");
        }

        Assert.assertNotNull(estados.getCities());*/
    }


    @Test
    public void obtenerMonedas() throws IOException{
        ServicioUbicacionMercadoLibre servicioMonedas = ServicioUbicacionMercadoLibre.instancia();
        List<Moneda> monedas = servicioMonedas.listadoMonedas();

        if ( monedas != null){
            for(Moneda infoMoneda: monedas){
                System.out.println("ID: " + infoMoneda.getId() + "| Simbolo: " + infoMoneda.getSymbol() + " | Descripcion: " + infoMoneda.getDescription());
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

        /*miDireccion.configurarDireccionPostal("Argentina", "Córdoba", "La Carlota", direccionCasa);

        Assert.assertEquals("La Carlota", miDireccion.getCiudad().getName());
        Assert.assertEquals("Argentina", miDireccion.getPais().getName());
        Assert.assertEquals("Córdoba", miDireccion.getProvincia().getName());*/
    }
}
