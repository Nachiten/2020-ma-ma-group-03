package ApiMercadoLibre;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioPaisMercadoLibre {

        private static ApiMercadoLibre.ServicioPaisMercadoLibre instancia = null;
        private final Retrofit retrofit;

        private ServicioPaisMercadoLibre() {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl("https://https://api.mercadolibre.com/classified_locations/countries/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public static ServicioPaisMercadoLibre instancia(){
            if(instancia== null){
                instancia = new ApiMercadoLibre.ServicioPaisMercadoLibre();
            }
            return instancia;
        }


    public ListadoPaises listadoDePaises(){
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        return (ListadoPaises) ubicacionService.paises();
    }
}