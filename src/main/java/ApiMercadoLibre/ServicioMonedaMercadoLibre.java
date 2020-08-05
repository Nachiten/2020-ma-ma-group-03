package ApiMercadoLibre;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioMonedaMercadoLibre {

    private static ServicioMonedaMercadoLibre instancia = null;
    private Retrofit retrofit;

    private ServicioMonedaMercadoLibre() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/currencies/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioMonedaMercadoLibre instancia(){
        if(instancia== null){
            instancia = new ServicioMonedaMercadoLibre();
        }
        return instancia;
    }

/*
    public ListadoDeProvincias listadoDeProvincias() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias();
        Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        ListadoDeProvincias provinciasArgentinas = responseProvinciasArgentinas.body();
        return provinciasArgentinas;
    }

    public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
        Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        ListadoDeMunicipios listadoDeMunicipios = responseListadoDeMunicipios.body();
        return listadoDeMunicipios;
    }

 */
}
