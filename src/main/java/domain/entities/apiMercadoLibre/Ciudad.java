package domain.entities.apiMercadoLibre;

import javax.persistence.*;

@Entity
@Table(name = "ciudades")
public class Ciudad {

    @Id
    String id;

    @Column(name = "name")
    String name;

    @ManyToOne (optional = false)
    @JoinColumn(name = "provinciaAsociada_id")
    Estado provinciaAsociada;

    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Ciudad() {
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

    public Estado getProvinciaAsociada() {
        return provinciaAsociada;
    }

    //-------------------------------------------------------------------------
                            //SETTERS
    //-------------------------------------------------------------------------


    public void setProvinciaAsociada(Estado provinciaAsociada) {
        this.provinciaAsociada = provinciaAsociada;
    }

    public void setId(String id) {
        this.id = id;
    }
}
