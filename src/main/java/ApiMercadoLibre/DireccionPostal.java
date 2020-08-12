package ApiMercadoLibre;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DireccionPostal {
    Direccion direccion;
    Ciudad ciudad;
    InfoEstado provincia;
    Pais pais;

    public void configurarDireccionPostal(String nombrePais, String nombreProvincia, String nombreCiudad) throws IOException {

        ServicioUbicacionMercadoLibre servicioMercadoLibre = ServicioUbicacionMercadoLibre.instancia();

        configurarPais(nombrePais, servicioMercadoLibre);

        configurarProvincia(nombreProvincia, servicioMercadoLibre);

        configurarCiudad(nombreCiudad);
    }

    private void configurarPais(String nombrePais, ServicioUbicacionMercadoLibre servicioMercadoLibre) throws IOException {

        List<Pais> paises = servicioMercadoLibre.listadoDePaises();

        Optional<Pais> paisPosible = paises.stream().filter( unPais -> unPais.getName().equals(nombrePais)).findFirst();

        if (paisPosible.isPresent()) {
            setPais(paisPosible.get());
        } else {
            System.out.println("El pais solicitado no existe");
        }

    }

    private void configurarProvincia(String nombreProvincia, ServicioUbicacionMercadoLibre servicioMercadoLibre) throws IOException {

        InfoPais infoPaisSeleccionado = servicioMercadoLibre.listadoEstadosDePais(pais.getId());

        Optional<Estado> estadoEncontrado = infoPaisSeleccionado.getStates().stream().filter(unEstado -> unEstado.getName().equals(nombreProvincia)).findFirst();


        if (estadoEncontrado.isPresent()) {

            Estado estadoFinal = estadoEncontrado.get();

            // Encontrar la info del estado a partir del idEstado
            InfoEstado estadoAAsignar = servicioMercadoLibre.listadoCiudadesDeEstado(estadoFinal.getId());

            setProvincia(estadoAAsignar);

        } else {
            System.out.println("La provincia seleccionada no existe dentro del pais");
        }
    }

    private void configurarCiudad(String nombreCiudad){

        Optional<Ciudad> ciudadPosible = provincia.getCities().stream().filter(unaCiudad -> unaCiudad.getName().equals(nombreCiudad)).findFirst();

        if (ciudadPosible.isPresent()) {

            Ciudad ciudadFinal = ciudadPosible.get();
            setCiudad(ciudadFinal);

        } else {
            System.out.println("La ciudad solicitada no existe dentro de la provincia seleccionada");
        }
    }

    private void setPais(Pais pais) {
        this.pais = pais;
    }

    public void setProvincia(InfoEstado provincia) {
        this.provincia = provincia;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
