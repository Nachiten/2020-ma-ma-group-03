package ApiMercadoLibre;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioUbicacionMercadoLibre {

    private static ServicioUbicacionMercadoLibre instancia = null;
    private Retrofit retrofit;

    private ServicioUbicacionMercadoLibre() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/classified_locations/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioUbicacionMercadoLibre instancia(){
        if(instancia== null){
            instancia = new ServicioUbicacionMercadoLibre();
        }
        return instancia;
    }
}
