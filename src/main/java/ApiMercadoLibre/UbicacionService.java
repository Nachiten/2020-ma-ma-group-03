package ApiMercadoLibre;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface UbicacionService {
        @GET("countries")
        Call<List<Pais>> paises();

        //@GET("countries/AR")
        //Call<InfoPais> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("max") int max);
}
