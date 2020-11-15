package domain.entities.apiMercadoLibre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiMercadoLibreInfo {

    static List<String> paisesId;
    static List<Pais> listadoPaises;
    static List<Estado> listadoProvincias;
    static List<Ciudad> listadoCiudades;
    static List<Moneda> monedas;
    static  ServicioUbicacionMercadoLibre servicioPais;

    //-------------------------------------------------------------------------
                            //METODOS
    //-------------------------------------------------------------------------

    static {
        try {
            servicioPais = ServicioUbicacionMercadoLibre.instancia();

            paisesId = generarListaIdDePaises();
            listadoPaises = generarListaDePaises();
            listadoProvincias = generarListaEstados();
            listadoCiudades = generarListaCiudades();
            monedas = generarListadoMonedas();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //se genera la lista de id de los paises ok
    private static List<String> generarListaIdDePaises() throws IOException {
        return servicioPais.listadoIdDePaises();
    }

    //se genera la lista de paises ok
    private static List<Pais> generarListaDePaises() throws IOException {
        List<Pais> listaPaises = new ArrayList<>();
        for (String unIdPais : paisesId){
            Pais unPais = servicioPais.obtenerPais(unIdPais);
            listaPaises.add(unPais);
        }

        return listaPaises;
    }

    //se genera la lista de estados ok
    private static List<Estado> generarListaEstados() throws IOException{
        List<Estado> listaEstados = new ArrayList<>();
        for (Pais unPais : listadoPaises) {
            //genero la lista de provincias por cada pais
            for (String unEstadoId : unPais.listadoIdProvincias()) {
                Estado unaProvincia = servicioPais.obtenerUnEstado(unEstadoId);
                //asocio el pais a la provincia
                unaProvincia.setPaisAsociado(unPais);
                listaEstados.add(unaProvincia);
            }
        }
        return listaEstados;
    }

    //se genera la lista de ciudades ok
    private static List<Ciudad> generarListaCiudades() throws IOException{
        List<Ciudad> listaCiudades = new ArrayList<>();
        for (Estado unaProvincia : listadoProvincias) {
            //genero la lista de ciudades por provincia
            for (String unaCiudadId : unaProvincia.listadoIdCiudades()) {
                Ciudad unaCiudad = servicioPais.obtenerUnaCiudad(unaCiudadId);
                //asocio la provincia a la ciudad
                unaCiudad.setProvinciaAsociada(unaProvincia);
                listaCiudades.add(unaCiudad);
            }
        }
        return listaCiudades;
    }

    //se genera la lista de todas la monedas ok
    private static List<Moneda> generarListadoMonedas() throws IOException {
        return servicioPais.listadoMonedas();
    }

    //-------------------------------------------------------------------------
                        //GETTERS
    //-------------------------------------------------------------------------

    public static List<Pais> getListadoPaises() { return listadoPaises; }

    public static List<Estado> getListadoProvincias() { return listadoProvincias; }

    public static List<Ciudad> getListadoCiudades() {
        return listadoCiudades;
    }

    public static List<Moneda> getMonedas() { return monedas; }
}
