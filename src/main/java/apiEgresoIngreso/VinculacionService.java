package apiEgresoIngreso;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


import java.util.List;

public interface VinculacionService {

    @Headers({"Accept: application/json"})
    @POST("/vinculacion")
    Call<List<String>>ejecutarCriterio(@Query("egresos") List<String> egresos, @Query("ingresos") List<String> ingreso, @Query("criterios") List<String> criterio);
}
