package ApiMercadoLibre;

import javax.persistence.*;

@Entity
@Table(name = "ciudades")
public class InfoCiudad {

    @Id
    String id;

    @Column(name = "name")
    String name;

    @ManyToOne (optional = false)
    @JoinColumn(name = "paisAsociado_id")
    InfoEstado provinciaAsociada;

    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public InfoCiudad() {
    }

    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
