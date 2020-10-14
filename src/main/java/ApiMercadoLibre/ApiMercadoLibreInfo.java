package ApiMercadoLibre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Excepciones.ExcepcionApiMercadoLibre;

public class ApiMercadoLibreInfo {

    static List<Pais> paises;
    static List<InfoPais> paisConListadoProvincias;
    static List<InfoEstado> provinciasConListadoCiudades;

    static {
        try {
            paises = generarListaPaises();
            paisConListadoProvincias = generarListaProvincias();
            provinciasConListadoCiudades = generarListaEstados();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Pais> generarListaPaises() throws IOException {
        // Aca se guarda la lista de todos los paises traidos de la API
        ServicioUbicacionMercadoLibre servicioPais = ServicioUbicacionMercadoLibre.instancia();
        return servicioPais.listadoDePaises();
    }

    private static List<InfoPais> generarListaProvincias() throws IOException {

        ServicioUbicacionMercadoLibre servicioPais = ServicioUbicacionMercadoLibre.instancia();

        List<InfoPais> listaPaises = new ArrayList<>();

        // Guardar lista de todas las provincias por cada pais
        for (Pais unPais : paises){
            InfoPais provinciasDePais = servicioPais.listadoEstadosDePais(unPais.getId());

            listaPaises.add(provinciasDePais);
        }

        return listaPaises;
    }

    private static List<InfoEstado> generarListaEstados() throws IOException{

        ServicioUbicacionMercadoLibre servicioPais = ServicioUbicacionMercadoLibre.instancia();

        List<InfoEstado> listaEstados = new ArrayList<>();

        // Guardar lista de todos los estados por cada provincia
        for (InfoPais infoPais : paisConListadoProvincias) {
            for (InfoEstado estado : infoPais.getStates()) {
                InfoEstado ciudadesPorProvincia = servicioPais.listadoCiudadesDeEstado(estado.getId());
                listaEstados.add(ciudadesPorProvincia);
            }
        }
        return listaEstados;
        /*for (InfoEstado infoEstado : provinciasConListadoCiudades){
            List<Estado> estados = infoEstado.getCities();

            for (Estado unEstado : estados){
                InfoEstado ciudadesDeProvincia = servicioPais.listadoCiudadesDeEstado(unEstado.getId());
                estadosTotales.add(ciudadesDeProvincia);
            }
        }*/

        //return estadosTotales;
    }

    /*
    public Pais obtenerPaisDeId(String IDPais) throws ExcepcionApiMercadoLibre{
        Optional<Pais> paisEncontrado = paises.stream().filter( unPais -> unPais.getId().equals(IDPais)).findFirst();

        if (paisEncontrado.isPresent()) {
            return paisEncontrado.get();
        } else {
            throw new ExcepcionApiMercadoLibre("No existe el pais buscado");
        }
    }

    public Estado obtenerEstadoDeId(String IDEstado) throws ExcepcionApiMercadoLibre{

        Estado estadoFinalEncontrado = null;

        for (InfoPais infoPais : provincias){
            Optional<Estado> estadoEncontrado = infoPais.getStates().stream().filter( unPais -> unPais.getId().equals(IDEstado)).findFirst();
            if (estadoEncontrado.isPresent()){
                estadoFinalEncontrado = estadoEncontrado.get();
                break;
            }
        }

        if (estadoFinalEncontrado != null) {
            return estadoFinalEncontrado;
        } else {
            throw new ExcepcionApiMercadoLibre("No existe el estado buscado");
        }
    }

    public Ciudad obtenerCiudadDeId(String IDCiudad) throws ExcepcionApiMercadoLibre{

        Ciudad ciudadFinal = null;

        for (InfoEstado infoEstado : ciudades){
            Optional<Ciudad> ciudadEncontrada = infoEstado.getCities().stream().filter( unPais -> unPais.getId().equals(IDCiudad)).findFirst();
            if (ciudadEncontrada.isPresent()){
                ciudadFinal = ciudadEncontrada.get();
                break;
            }
        }

        if (ciudadFinal != null) {
            return ciudadFinal;
        } else {
            throw new ExcepcionApiMercadoLibre("No existe la ciudad buscada");
        }
    }*/

   /* public void cargarMonedaPorPais(String idPais) throws IOException, ExcepcionApiMercadoLibre {
        Optional<InfoPais> paisEncontrado = paisConListadoProvincias.stream().filter( unaProvincia -> unaProvincia.getId().equals(idPais)).findFirst();

        if (paisEncontrado.isPresent()){
            ServicioUbicacionMercadoLibre servicioPais = ServicioUbicacionMercadoLibre.instancia();
            InfoMoneda monedaPorPais = servicioPais.listarMonedaPorPais(paisEncontrado.get().getId());
             paisEncontrado.get().setCurrency_id(monedaPorPais);
        } else {
            throw new ExcepcionApiMercadoLibre("No existe el pais solicitado");
        }
    }*/

    public static InfoPais obtenerProvinciasDePais(String idPais) throws ExcepcionApiMercadoLibre{
        Optional<InfoPais> paisEncontrado = paisConListadoProvincias.stream().filter( unaProvincia -> unaProvincia.getId().equals(idPais)).findFirst();

        if (paisEncontrado.isPresent()){
            return paisEncontrado.get();
        } else {
            throw new ExcepcionApiMercadoLibre("No existe el pais solicitado");
        }
    }

    public static InfoEstado obtenerCiudadesDeEstado(String idEstado) throws ExcepcionApiMercadoLibre{
        Optional<InfoEstado> estadoEncontrado = provinciasConListadoCiudades.stream().filter( unaCiudad -> unaCiudad.getId().equals(idEstado)).findFirst();

        if (estadoEncontrado.isPresent()){
            return estadoEncontrado.get();
        } else {
            throw new ExcepcionApiMercadoLibre("No existe el estado solicitado");
        }
    }


    public static List<Pais> getPaises() { return paises; }

    public static List<InfoPais> getPaisConListadoProvincias() {
        return paisConListadoProvincias;
    }

    public static List<InfoEstado> getProvinciasConListadoCiudades() {
        return provinciasConListadoCiudades;
    }
}
