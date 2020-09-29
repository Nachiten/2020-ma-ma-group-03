package model;

import main.GsonConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CriterioEjecucionMix extends Validacion implements CriterioEjecucion {

    private final List<CriterioEjecucion> criterios;

    @Override
    public List<String> ejecutarse(List<OperacionDeEgreso> egresos, List<OperacionDeIngreso> ingresos){
        GsonConverter gsonConverter = new GsonConverter();
        List<OperacionDeIngreso> ingresosParciales = new ArrayList<>(ingresos);
        List<OperacionDeEgreso> egresosParciales = new ArrayList<>(egresos);
        for (CriterioEjecucion criterio : criterios) {
            List<String> ingresosGsonParciales = criterio.ejecutarse(egresosParciales, ingresosParciales);
            ingresosParciales = gsonConverter.convertirIngresosGsonAIngresos(ingresosGsonParciales);
            egresosParciales = egresosParciales.stream().filter(operacionDeEgreso -> !operacionDeEgreso.fueVinculada()).collect(Collectors.toList());
        }

        return gsonConverter.convertirIngresosAGson(ingresos);
    }

    public CriterioEjecucionMix(){
        this.criterios = new ArrayList<>();
    }

    public void agregarCriterio(CriterioEjecucion criterio){
        this.criterios.add(criterio);
    }
}