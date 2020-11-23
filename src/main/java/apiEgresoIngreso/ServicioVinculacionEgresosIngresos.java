package ApiEgresoIngreso;

import domain.entities.operaciones.OperacionDeEgreso;
import domain.entities.operaciones.OperacionDeIngreso;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
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
        List<OperacionDeEgreso> egresosSimples = simplificarEgresos(egresos);
        OperacionDeEgreso egresoRompeHuevos = egresosSimples.get(0);
        egresoRompeHuevos.setFueVinculada(true);
        List<OperacionDeIngreso> ingresosSimples = simplificarIngresos(ingresos);
        List<String> egresosGson = gsonConverter.convertirEgresosAGson(egresosSimples);
        List<String> ingresosGson = gsonConverter.convertirIngresosAGson(ingresosSimples);
        Call<List<String>> operacionesDeIngreso = vinculacionService.ejecutarCriterio(egresosGson, ingresosGson, criterio);
        Response<List<String>> responseOperacionesDeIngreso = operacionesDeIngreso.execute();
        List<String> operacionesDeIngresoFinales = responseOperacionesDeIngreso.body();
        assert operacionesDeIngresoFinales != null;
        return gsonConverter.convertirIngresosGsonAIngresos(operacionesDeIngresoFinales);
    }

    private List<OperacionDeEgreso> simplificarEgresos(List<OperacionDeEgreso> egresos){
        List<OperacionDeEgreso> egresosSimples = new ArrayList<>();
        for(int i = 0; i < egresos.size(); i++){
            OperacionDeEgreso unEgreso = egresos.get(i);
            OperacionDeEgreso egresoSimple = new OperacionDeEgreso(unEgreso.getIdOperacion(), unEgreso.getFecha(), unEgreso.getMontoTotal(), unEgreso.getOperacionDeIngreso(), unEgreso.fueVinculada());
            egresosSimples.add(egresoSimple);
        }
        return egresosSimples;
    }

    private List<OperacionDeIngreso> simplificarIngresos(List<OperacionDeIngreso> ingresos){
        List<OperacionDeIngreso> ingresosSimples = new ArrayList<>();
        for(int i = 0; i < ingresos.size(); i++){
            OperacionDeIngreso unIngreso = ingresos.get(i);
            OperacionDeIngreso ingresoSimple = new OperacionDeIngreso(unIngreso.getDescripcion(), unIngreso.getMontoTotal(), unIngreso.getFecha(), unIngreso.getId(), unIngreso.getPeriodoAceptacion());
            ingresosSimples.add(ingresoSimple);
        }
        return ingresosSimples;
    }
}