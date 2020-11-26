package domain.entities.apiMercadoLibre;

import excepciones.ExcepcionApiMercadoLibre;
import persistencia.EntidadPersistente;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Entity
@Table (name = "direccionPostal")
public class DireccionPostal extends EntidadPersistente {

    @ManyToOne (cascade = {CascadeType.ALL})
    private Direccion direccion;

    @ManyToOne (cascade = {CascadeType.ALL})
    private Pais pais;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Estado provincia;

    @ManyToOne (cascade = {CascadeType.ALL})
    private Ciudad ciudad;

    //-------------------------------------------------------------------------
                        //CONTRUCTOR
    //-------------------------------------------------------------------------

    public DireccionPostal(){
    }

    public DireccionPostal(Direccion direccion, String barrio, Estado provincia, Pais pais){
        this.direccion = direccion;
        this.provincia = provincia;
        this.pais = pais;
    }

    public DireccionPostal(Direccion direccion, String barrio, Estado provincia, Pais pais,Ciudad ciudad){
        this.direccion = direccion;
        this.provincia = provincia;
        this.pais = pais;
        this.ciudad = ciudad;
    }

    public DireccionPostal(Direccion direccion){
        this.direccion = direccion;
    }

    //-------------------------------------------------------------------------
                        //METODOS
    //-------------------------------------------------------------------------

    public void asociarDireccion(Direccion direccionProveedor) {
        this.direccion = direccionProveedor;
    }
    /*
    public void configurarDireccionPostal(String nombrePais, String nombreProvincia, Direccion unaDireccion) {
        //configurarPais(nombrePais);
        //configurarProvincia(nombreProvincia);

        this.direccion = unaDireccion;
    }

   private void configurarPais(String nombrePais) {

        List<Pais> listaDePaises = ApiMercadoLibreInfo.getListadoPaises();

        Optional<Pais> paisPosible = listaDePaises.stream().filter(unPais -> (unPais.getName().equals(nombrePais))).findFirst();

        if (paisPosible.isPresent()) {

            setPais(paisPosible.get());
        } else {
            System.out.println("El pais solicitado no existe");
        }
    }*/

    //private void configurarProvincia(String nombreProvincia) throws ExcepcionApiMercadoLibre {

        /*Pais infoPaisSeleccionado = ApiMercadoLibreInfo.obtenerProvinciasDePais(pais.getId());

        Optional<Estado> estadoEncontrado = infoPaisSeleccionado.getStates().stream().filter(unEstado -> unEstado.getName().equals(nombreProvincia)).findFirst();

        if (estadoEncontrado.isPresent()) {

            Estado estadoFinal = estadoEncontrado.get();

            // Encontrar la info del estado a partir del idEstado
            Estado estadoAAsignar = ApiMercadoLibreInfo.obtenerCiudadesDeEstado(estadoFinal.getId());

            setProvincia(estadoAAsignar);

        } else {
            System.out.println("La provincia seleccionada no existe dentro del pais");
        }*/
    //}

    //-------------------------------------------------------------------------
                        //SETTERS
    //-------------------------------------------------------------------------

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void setProvincia(Estado provincia) {
        this.provincia = provincia;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    //-------------------------------------------------------------------------
                        //GETTERS
    //-------------------------------------------------------------------------

    public Estado getProvincia() {
        return provincia;
    }

    public Pais getPais() {
        return pais;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }


}
