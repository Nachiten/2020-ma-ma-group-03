package ApiMercadoLibre;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "paises")
public class InfoPais {
    @Id
    String id;

    @Column(name = "name")
    String name;

    @Column (name = "locale")
    String locale;

    //@OneToOne(cascade = {CascadeType.ALL})
    //InfoMoneda currency_id;

    @OneToMany(mappedBy = "paisAsociado", cascade = {CascadeType.ALL})
    List<InfoEstado> states;

    public InfoPais() {
    }

    public List<InfoEstado> getStates() {
        return states;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocale() {
        return locale;
    }

    /*public InfoMoneda getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(InfoMoneda currency_id) {
        this.currency_id = currency_id;
    }*/
}
