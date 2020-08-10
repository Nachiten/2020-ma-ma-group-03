package ApiMercadoLibre;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

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

    public List<Pais> listadoDePaises() throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<List<Pais>> pedidoPaises = ubicacionService.paises();
        Response<List<Pais>> responseProvinciasArgentinas = pedidoPaises.execute();
        List<Pais> listaPaises = responseProvinciasArgentinas.body();
        return listaPaises;
        //return (ListadoPaises) ubicacionService.paises();
    }
}
