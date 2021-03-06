package domain.entities.apiMercadoLibre;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provincias")
public class Estado {

    @Id
    String id;

    @Column(name = "name")
    String name;

    @ManyToOne (optional = false)
    @JoinColumn(name = "paisAsociado_id")
    Pais paisAsociado;

    @Transient
    List<Lista> cities;


    //-------------------------------------------------------------------------
                    //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Estado() {
    }

    public Estado(String provincia){
        this.name = provincia;
    }

    //-------------------------------------------------------------------------
                    //METODOS
    //-------------------------------------------------------------------------

    public List<String> listadoIdCiudades(){
        List<String> listadoId = new ArrayList<>();
        for (Lista unaCiudad : cities) {
            listadoId.add(unaCiudad.getId());
        }
        return listadoId;
    }

    //-------------------------------------------------------------------------
                    //GETTERS
    //-------------------------------------------------------------------------

    public List<Lista> getCities() {
        return cities;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Pais getPaisAsociado() {
        return paisAsociado;
    }

    //-------------------------------------------------------------------------
                    //SETTERS
    //-------------------------------------------------------------------------


    public void setPaisAsociado(Pais paisAsociado) {
        this.paisAsociado = paisAsociado;
    }
}
