package ApiMercadoLibre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Excepciones.ExcepcionApiMercadoLibre;

public class ApiMercadoLibreInfo {
    List<Pais> paises;
    List<InfoPais> provincias;
    List<InfoEstado> ciudades;

    public ApiMercadoLibreInfo(){
        paises = new ArrayList<>();
        provincias = new ArrayList<>();
        ciudades = new ArrayList<>();
    }

    public void obtenerDatosApiMercadoLibre() throws IOException{
        ServicioUbicacionMercadoLibre servicioPais = ServicioUbicacionMercadoLibre.instancia();

        llenarListaPaises(servicioPais);

        llenarListaProvincias(servicioPais);

        llenarListaEstados(servicioPais);
    }

    private void llenarListaPaises(ServicioUbicacionMercadoLibre servicioPais) throws IOException {
        // Aca se guarda la lista de todos los paises traidos de la API
        this.paises.addAll( servicioPais.listadoDePaises() );
    }

    private void llenarListaProvincias(ServicioUbicacionMercadoLibre servicioPais) throws IOException {
        // Guardar lista de todas las provincias por cada pais
        for (Pais unPais : this.paises){
            InfoPais provinciasDePais = servicioPais.listadoEstadosDePais(unPais.getId());

            provincias.add(provinciasDePais);
        }
    }

    private void llenarListaEstados(ServicioUbicacionMercadoLibre servicioPais) throws IOException{
        // Guardar lista de todos los estados por cada pais
        for (InfoPais infoPais : this.provincias){
            List<Estado> estados = infoPais.getStates();

            for (Estado unEstado : estados){
                InfoEstado ciudadesDeProvincia = servicioPais.listadoCiudadesDeEstado(unEstado.getId());
                ciudades.add(ciudadesDeProvincia);
            }

        }
    }

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
    }

    public InfoPais obtenerProvinciasDePais(String idPais) throws ExcepcionApiMercadoLibre{
        Optional<InfoPais> paisEncontrado = provincias.stream().filter( unaProvincia -> unaProvincia.getId().equals(idPais)).findFirst();

        if (paisEncontrado.isPresent()){
            return paisEncontrado.get();
        } else {
            throw new ExcepcionApiMercadoLibre("No existe el pais solicitado");
        }
    }

    public InfoEstado obtenerCiudadesDeEstado(String idEstado) throws ExcepcionApiMercadoLibre{
        Optional<InfoEstado> estadoEncontrado = ciudades.stream().filter( unaCiudad -> unaCiudad.getId().equals(idEstado)).findFirst();

        if (estadoEncontrado.isPresent()){
            return estadoEncontrado.get();
        } else {
            throw new ExcepcionApiMercadoLibre("No existe el estado solicitado");
        }
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public List<InfoEstado> getCiudades() {
        return ciudades;
    }

    public List<InfoPais> getProvincias() {
        return provincias;
    }
}
