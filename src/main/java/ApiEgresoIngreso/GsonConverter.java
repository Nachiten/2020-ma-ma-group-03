package ApiEgresoIngreso;

import Operaciones.OperacionDeEgreso;
import Operaciones.OperacionDeIngreso;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GsonConverter {

    public List<String> convertirEgresosAGson(List<OperacionDeEgreso> egresos){
        List<String> operacionesGson = new ArrayList<>();
        Gson gson = new Gson();
        for (OperacionDeEgreso operacionDeEgreso : egresos) {
            String operacionEgresoGson = gson.toJson(operacionDeEgreso);
            operacionesGson.add(operacionEgresoGson);
        }
        return operacionesGson;
    }

    public List<String> convertirIngresosAGson(List<OperacionDeIngreso> ingresos){
        List<String> operacionesIngresoGson = new ArrayList<>();
        Gson gson = new Gson();
        for (OperacionDeIngreso operacionDeIngreso : ingresos) {
            String opIngresoGson = gson.toJson(operacionDeIngreso);
            operacionesIngresoGson.add(opIngresoGson);
        }
        return operacionesIngresoGson;
    }

    public List<OperacionDeIngreso> convertirIngresosGsonAIngresos(List<String> ingresosGson){

        final Gson gson = new Gson();
        List<OperacionDeIngreso> operacionesDeIngreso = new ArrayList<>();

        for(String operacionIngresoGson : ingresosGson){
            final OperacionDeIngreso ingreso = gson.fromJson(operacionIngresoGson, OperacionDeIngreso.class);
            operacionesDeIngreso.add(ingreso);
        }
        return operacionesDeIngreso;
    }

}