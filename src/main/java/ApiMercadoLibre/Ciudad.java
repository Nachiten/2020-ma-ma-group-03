package ApiMercadoLibre;

import Persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @GeneratedValue
    String id;
    @Column (name = "name")
    String name;

    @ManyToOne (cascade = {CascadeType.ALL})
    private InfoEstado infoEstadoAsociado;

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