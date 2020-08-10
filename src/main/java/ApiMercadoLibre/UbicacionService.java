package ApiMercadoLibre;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UbicacionService {

        @GET("countries")
        Call<ListadoPaises> paises();
    }
