package ApiMercadoLibre;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provincias")
public class InfoEstado {

    @Id
    String id;

    @Column(name = "name")
    String name;

    @ManyToOne (optional = false)
    @JoinColumn(name = "paisAsociado_id")
    InfoPais paisAsociado;

    @OneToMany (mappedBy = "provinciaAsociada", cascade = {CascadeType.ALL})
    List<InfoCiudad> cities;


    //-------------------------------------------------------------------------
                    //CONTRUCTOR
    //-------------------------------------------------------------------------

    public InfoEstado() {
    }


    //-------------------------------------------------------------------------
                    //GETTERS
    //-------------------------------------------------------------------------

    public List<InfoCiudad> getCities() {
        return cities;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
