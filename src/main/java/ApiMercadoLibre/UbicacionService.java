package ApiMercadoLibre;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface UbicacionService {
        // Obtener la lista de todos los paises
        @GET("classified_locations/countries")
        Call<List<Pais>> paises();

        // Obtener la lista de provincias (estados) a partir de un pais (informacion del pais)
        @GET("classified_locations/countries/{idPais}")
        Call<InfoPais> provincias(@Path("idPais") String nombrePais, @Query("campos") String campos );

        // Obtener lista de ciudades a partir del estado (informacion del estado)
        @GET("classified_locations/states/{idEstado}")
        Call<InfoEstado> estados(@Path("idEstado") String nombreEstado, @Query("campos") String campos );

        // Obtener todas las monedas existentes
        @GET("currencies")
        Call<List<InfoMoneda>> monedas();

}
