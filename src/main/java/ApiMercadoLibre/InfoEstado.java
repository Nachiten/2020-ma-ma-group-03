package ApiMercadoLibre;

import java.util.List;

public class InfoEstado {
    String id;
    String name;
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
