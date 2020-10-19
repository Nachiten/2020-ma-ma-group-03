package domain.entities.apiMercadoLibre;

import com.google.gson.internal.LinkedTreeMap;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface UbicacionService {

        // Obtener la lista de todos los idPaises
        @GET("classified_locations/countries")
        Call<List<LinkedTreeMap>> idPaises();

        // Obtener la lista de pais (estados) a partir de un id de pais (informacion del pais)
        @GET("classified_locations/countries/{idPais}")
        Call<Pais> pais(@Path("idPais") String nombrePais, @Query("campos") String campos );

        // Obtener lista de ciudades a partir del id estado (informacion del estado)
        @GET("classified_locations/states/{idEstado}")
        Call<Estado> estados(@Path("idEstado") String nombreEstado, @Query("campos") String campos );

        //Obtener informacion de una ciudad a partir de id ciudad
        @GET("classified_locations/cities/{idCiudad}")
        Call<Ciudad> ciudad(@Path("idCiudad") String nombreCiudad, @Query("campos") String campos);

        //obtener monedas de cada pais
        @GET("currencies/{idPais}")
        Call<Monedas> monedas(@Path("idPais") String nombrePais, @Query("campos") String campos);

        // Obtener todas las monedas existentes
        @GET("currencies")
        Call<List<Monedas>> monedas();

}
