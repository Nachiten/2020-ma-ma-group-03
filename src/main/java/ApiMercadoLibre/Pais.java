package ApiMercadoLibre;

import Persistencia.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "pais")
public class Pais {

    @Id
    @GeneratedValue
    public String id;
    @Column(name = "name")
    public String name;
    @Column (name = "locale")
    public String locale;
    @Column (name = "currency_id")
    public String currency_id;

    public String getName() {
        return name;
    }

    public String getLocale() {
        return locale;
    }

    public String getId() {
        return id;
    }
}
