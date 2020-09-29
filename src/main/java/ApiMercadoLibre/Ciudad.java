package ApiMercadoLibre;

import Persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "ciudad")
public class Ciudad {

    @Id
    String id;
    @Column (name = "name")
    String name;

    @ManyToOne (cascade = {CascadeType.ALL})
    private InfoEstado infoEstadoAsociado;

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

    public InfoEstado getEstadoAsociado() {
        return infoEstadoAsociado;
    }
}