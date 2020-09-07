package ApiMercadoLibre;

import java.util.List;

public class InfoPais {
    String id;
    String name;
    String locale;
    List<Estado> states;

    public List<Estado> getStates() {
        return states;
    }

    public String getId() {
        return id;
    }
}
