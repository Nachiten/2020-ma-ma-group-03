package ApiMercadoLibre;

import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "estado")
public class InfoEstado {

    @Id
    @GeneratedValue
    String id;
    @Column(name = "name")
    String name;
    @OneToMany (mappedBy = "infoEstadoAsociado", cascade = {CascadeType.ALL})
    List<Ciudad> cities;

    public List<Ciudad> getCities() {
        return cities;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
