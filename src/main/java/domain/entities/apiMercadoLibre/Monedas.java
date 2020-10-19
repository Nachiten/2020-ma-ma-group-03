package domain.entities.apiMercadoLibre;

import javax.persistence.*;

@Entity
@Table(name = "monedas")
public class Monedas {
    @Id
    String id;

    @Column(name = "simbolo")
    String symbol;

    @Column(name = "descripcion")
    String description;

    @Column(name = "decimales")
    String decimal_places;


    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescripcion(){
        return description;
    }
}
