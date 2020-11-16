package ApiEgresoIngreso;

import domain.entities.operaciones.OperacionDeEgreso;
import domain.entities.operaciones.OperacionDeIngreso;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;


public class ServicioVinculacionEgresosIngresos {

    private static ServicioVinculacionEgresosIngresos instancia = null;
    private Retrofit retrofit;

    public ServicioVinculacionEgresosIngresos() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioVinculacionEgresosIngresos instancia(){
        if(instancia== null){
            instancia = new ServicioVinculacionEgresosIngresos();
        }
        return instancia;
    }

    public List<OperacionDeIngreso> ejecutarVinculacion(List<OperacionDeEgreso> egresos, List<OperacionDeIngreso> ingresos, List<String> criterio) throws IOException {
        apiEgresoIngreso.VinculacionService vinculacionService = this.retrofit.create(apiEgresoIngreso.VinculacionService.class);
        ApiEgresoIngreso.GsonConverter gsonConverter = new ApiEgresoIngreso.GsonConverter();
        List<String> egresosGson = gsonConverter.convertirEgresosAGson(egresos); //todo no puede convertirlo a gson
        List<String> ingresosGson = gsonConverter.convertirIngresosAGson(ingresos);
        Call<List<String>> operacionesDeIngreso = vinculacionService.ejecutarCriterio(egresosGson, ingresosGson, criterio);
        Response<List<String>> responseOperacionesDeIngreso = operacionesDeIngreso.execute();
        List<String> operacionesDeIngresoFinales = responseOperacionesDeIngreso.body();
        assert operacionesDeIngresoFinales != null;
        return gsonConverter.convertirIngresosGsonAIngresos(operacionesDeIngresoFinales);
    }
}