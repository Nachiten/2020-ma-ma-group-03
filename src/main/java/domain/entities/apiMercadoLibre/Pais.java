package domain.entities.apiMercadoLibre;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "paises")
public class Pais {
    @Id
    String id;

    @Column(name = "name")
    String name;

    @Column (name = "locale")
    String locale;

    @Column (name = "currency_id")
    String currency_id;

    @Transient
    List<Lista> states;

    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Pais() {
    }

    //-------------------------------------------------------------------------
                            //METODOS
    //-------------------------------------------------------------------------

    public List<String> listadoIdProvincias(){
        List<String> listadoId = new ArrayList<>();
        for (Lista unEstado : states) {
            listadoId.add(unEstado.getId());
        }
        return listadoId;
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

    public String getLocale() {
        return locale;
    }

    public String getCurrency_id() {
        return currency_id;
    }

}
