package main;

import com.google.gson.Gson;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class GsonConverter {

    public List<OperacionDeEgreso> convertirEgresosGsonAEgresos(List<String> egresosGson){

        final Gson gson = new Gson();
        List<OperacionDeEgreso> operacionesDeEgreso = new ArrayList<>();

        for(String operacionEgresoGson : egresosGson){
            OperacionDeEgreso egreso = gson.fromJson(operacionEgresoGson, OperacionDeEgreso.class);
            operacionesDeEgreso.add(egreso);
        }
        return operacionesDeEgreso;
    }

    public List<OperacionDeIngreso> convertirIngresosGsonAIngresos(List<String> ingresosGson){

        final Gson gson = new Gson();
        List<OperacionDeIngreso> operacionesDeIngreso = new ArrayList<>();

        for(String operacionIngresoGson : ingresosGson){
            OperacionDeIngreso ingreso = gson.fromJson(operacionIngresoGson, OperacionDeIngreso.class);
            operacionesDeIngreso.add(ingreso);
        }
        return operacionesDeIngreso;
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

    public CriterioEjecucion convertirCriterioGsonACriterio(String criterioGson){
        CriterioEjecucion criterioFinal = null;
        if(criterioGson.equals("ordenValorPrimeroEgreso")){
            criterioFinal = new CriterioEjecucionPrimeroEgreso();
        }
        if(criterioGson.equals("ordenValorPrimeroIngreso")){
            criterioFinal = new CriterioEjecucionPrimeroIngreso();
        }
        if(criterioGson.equals("fecha")){
            criterioFinal = new CriterioEjecucionFecha();
        }
        return criterioFinal;
    }

    public CriterioEjecucion convertirCriteriosGsonACriterio(List<String> criterios){
        CriterioEjecucion criterioFinal;
        CriterioEjecucionMix mix;
        String criterioGson = criterios.get(0);

        criterioFinal = convertirCriterioGsonACriterio(criterioGson);

        if(criterioGson.equals("mix")){
            mix = new CriterioEjecucionMix();
            asignarCriteriosAMix(criterios, mix);
            criterioFinal = mix;
        }
        return criterioFinal;
    }

    public void asignarCriteriosAMix(List<String> criterios, CriterioEjecucionMix mix){

        for(int i = 1; i < criterios.size(); i++){
            String criterioGsonEnMix = criterios.get(i);
            CriterioEjecucion criterioMix = convertirCriterioGsonACriterio(criterioGsonEnMix);
            mix.agregarCriterio(criterioMix);
        }
    }
}