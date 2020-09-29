package ApiMercadoLibre;


public class Pais {


    public String id;
    public String name;
    public String locale;
    public String currency_id;


    //-------------------------------------------------------------------------
                        //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Pais() {
    }


    //-------------------------------------------------------------------------
                        //GETTERS
    //-------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public String getLocale() {
        return locale;
    }

    public String getId() {
        return id;
    }

    public String getCurrency_id() { return currency_id; }
}
