package ApiMercadoLibre;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UbicacionService {

        @GET("paises")
        Call<ListadoPaises> paises();
    }
