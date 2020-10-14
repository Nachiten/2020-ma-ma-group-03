package ApiMercadoLibre;

import Excepciones.ExcepcionApiMercadoLibre;
import Persistencia.EntidadPersistente;

import javax.persistence.*;
import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Entity
@Table (name = "direccionPostal")
public class DireccionPostal extends EntidadPersistente {
    @ManyToOne (cascade = {CascadeType.ALL})
    private Direccion direccion;

    @ManyToOne (cascade = {CascadeType.ALL})
    private InfoCiudad ciudad;

    @ManyToOne (cascade = {CascadeType.ALL})
    private InfoEstado provincia;

    @ManyToOne (cascade = {CascadeType.ALL})
    private InfoPais pais;

    //-------------------------------------------------------------------------
                        //CONTRUCTOR
    //-------------------------------------------------------------------------

    public DireccionPostal(){
    }

    //-------------------------------------------------------------------------
                        //METODOS
    //-------------------------------------------------------------------------

    public void configurarDireccionPostal(String nombrePais, String nombreProvincia, String nombreCiudad, Direccion unaDireccion) throws IOException, ExcepcionApiMercadoLibre {
        //configurarPais(nombrePais);
        configurarProvincia(nombreProvincia);
        configurarCiudad(nombreCiudad);

        this.direccion = unaDireccion;
    }

    private void configurarPais(String nombrePais) throws IOException {

        List<Pais> listaDePaises = ApiMercadoLibreInfo.getPaises();

        Optional<Pais> paisPosible = listaDePaises.stream().filter(unPais -> unPais.getName().equals(nombrePais)).findFirst();

        if (paisPosible.isPresent()) {
            //setPais(paisPosible.get());
        } else {
            System.out.println("El pais solicitado no existe");
        }

    }

    private void configurarProvincia(String nombreProvincia) throws IOException, ExcepcionApiMercadoLibre {

        InfoPais infoPaisSeleccionado = ApiMercadoLibreInfo.obtenerProvinciasDePais(pais.getId());

        Optional<InfoEstado> estadoEncontrado = infoPaisSeleccionado.getStates().stream().filter(unEstado -> unEstado.getName().equals(nombreProvincia)).findFirst();

        if (estadoEncontrado.isPresent()) {

            InfoEstado estadoFinal = estadoEncontrado.get();

            // Encontrar la info del estado a partir del idEstado
            InfoEstado estadoAAsignar = ApiMercadoLibreInfo.obtenerCiudadesDeEstado(estadoFinal.getId());

            setProvincia(estadoAAsignar);

        } else {
            System.out.println("La provincia seleccionada no existe dentro del pais");
        }
    }

    private void configurarCiudad(String nombreCiudad){

        Optional<InfoCiudad> ciudadPosible = provincia.getCities().stream().filter(unaCiudad -> unaCiudad.getName().equals(nombreCiudad)).findFirst();

        if (ciudadPosible.isPresent()) {

            InfoCiudad ciudadFinal = ciudadPosible.get();
            setCiudad(ciudadFinal);

        } else {
            System.out.println("La ciudad solicitada no existe dentro de la provincia seleccionada");
        }
    }

    //-------------------------------------------------------------------------
                        //SETTERS
    //-------------------------------------------------------------------------

    public void setPais(InfoPais pais) {
        this.pais = pais;
    }

    public void setProvincia(InfoEstado provincia) {
        this.provincia = provincia;
    }

    public void setCiudad(InfoCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    //-------------------------------------------------------------------------
                        //GETTERS
    //-------------------------------------------------------------------------

    public InfoCiudad getCiudad() {
        return ciudad;
    }

    public InfoEstado getProvincia() {
        return provincia;
    }

    public InfoPais getPais() {
        return pais;
    }
}
