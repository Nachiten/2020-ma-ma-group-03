package ApiMercadoLibre;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioPaisMercadoLibre {

        private static ApiMercadoLibre.ServicioPaisMercadoLibre instancia = null;
        private final Retrofit retrofit;

        private ServicioPaisMercadoLibre() {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.mercadolibre.com/classified_locations/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public static ServicioPaisMercadoLibre instancia(){
            if(instancia== null){
                instancia = new ServicioPaisMercadoLibre();
            }
            return instancia;
        }


    public ListadoPaises listadoDePaises() throws IOException{
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<ListadoPaises> pedidoPaises = ubicacionService.paises();
        Response<ListadoPaises> responseProvinciasArgentinas = pedidoPaises.execute();
        ListadoPaises listaPaises = responseProvinciasArgentinas.body();
        return listaPaises;
        //return (ListadoPaises) ubicacionService.paises();
    }
}