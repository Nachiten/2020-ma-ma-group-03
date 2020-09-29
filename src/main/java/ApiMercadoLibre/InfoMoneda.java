package ApiMercadoLibre;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "monedas")
public class InfoMoneda {
    @Id
    String id;

    String symbol;

    String description;

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
