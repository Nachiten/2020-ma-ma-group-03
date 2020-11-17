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
    @ManyToOne //(cascade = {CascadeType.ALL})
    private Direccion direccion;

    @ManyToOne //(cascade = {CascadeType.ALL})
    @Column (name = "barrio")
    private String barrio;

    @ManyToOne (cascade = {CascadeType.ALL})
    private Estado provincia;

    @ManyToOne //(cascade = {CascadeType.ALL})
    private Pais pais;

    //-------------------------------------------------------------------------
                        //CONTRUCTOR
    //-------------------------------------------------------------------------

    public DireccionPostal(){
    }

    public DireccionPostal(Direccion direccion,String barrio,Estado provincia ,Pais pais){
        this.direccion = direccion;
        this.barrio = barrio;
        this.provincia = provincia;
        this.pais = pais;
    }

    public DireccionPostal(Direccion direccion){
        this.direccion = direccion;
    }

    //-------------------------------------------------------------------------
                        //METODOS
    //-------------------------------------------------------------------------

    public void configurarDireccionPostal(String nombrePais, String nombreProvincia, Direccion unaDireccion) throws IOException, ExcepcionApiMercadoLibre {
        //configurarPais(nombrePais);
        configurarProvincia(nombreProvincia);

        this.direccion = unaDireccion;
    }

   private void configurarPais(String nombrePais) throws IOException {

        List<Pais> listaDePaises = ApiMercadoLibreInfo.getListadoPaises();

        Optional<Pais> paisPosible = listaDePaises.stream().filter(unPais -> (unPais.getName().equals(nombrePais))).findFirst();

        if (paisPosible.isPresent()) {

            setPais(paisPosible.get());
        } else {
            System.out.println("El pais solicitado no existe");
        }
    }

    private void configurarProvincia(String nombreProvincia) throws IOException, ExcepcionApiMercadoLibre {

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
    }

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
}
