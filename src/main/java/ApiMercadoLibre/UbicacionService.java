package ApiMercadoLibre;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface UbicacionService {
        // Obtener la lista de todos los paises
        @GET("countries")
        Call<List<Pais>> paises();

        // Obtener la lista de provincias a partir de un pais
        @GET("countries/{idPais}")
        Call<InfoPais> provincias(@Path("idPais") String nombrePais, @Query("campos") String campos );

}
