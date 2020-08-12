package ApiMercadoLibre;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DireccionPostal {
    Direccion direccion;
    Ciudad ciudad;
    InfoEstado provincia;
    Pais pais;

    public void configurarDireccionPostal(String nombrePais, String nombreProvincia, String nombreCiudad) throws IOException {
        // pedir la lista de paises
        // checkeo si ese pais existe
        // lo guardo en pais

        ServicioUbicacionMercadoLibre servicioMercadoLibre = ServicioUbicacionMercadoLibre.instancia();
        List<Pais> paises = servicioMercadoLibre.listadoDePaises();

        Optional<Pais> paisPosible = paises.stream().filter( unPais -> unPais.getName().equals(nombrePais)).findFirst();

        if (paisPosible.isPresent()) {
            setPais(paisPosible.get());
        } else {
            System.out.println("El pais solicitado no existe");
            return;
        }

        // Pido la info del pais
        InfoPais infoPaisSeleccionado = servicioMercadoLibre.listadoEstadosDePais(pais.getId());

        // Encuentro el estado pedido dentro del pais
        Optional<Estado> estadoEncontrado = infoPaisSeleccionado.getStates().stream().filter(unEstado -> unEstado.getName().equals(nombreProvincia)).findFirst();

        // Verificar si existe
        if (estadoEncontrado.isPresent()) {

            Estado estadoFinal = estadoEncontrado.get();

            // Encontrar la info del estado a partir del idEstado
            InfoEstado estadoAAsignar = servicioMercadoLibre.listadoCiudadesDeEstado(estadoFinal.getId());

            setProvincia(estadoAAsignar);

        } else {
            System.out.println("La provincia seleccionada no existe dentro del pais");
            return;
        }

        // Filtro para encontrar la ciudad pedida
        Optional<Ciudad> ciudadPosible = provincia.getCities().stream().filter(unaCiudad -> unaCiudad.getName().equals(nombreCiudad)).findFirst();

        // Verificar si existe
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
