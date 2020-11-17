package model;

import main.VinculacionService;

import java.util.List;

public class CriterioEjecucionPrimeroIngreso extends VinculacionService implements CriterioEjecucion {

    public List<String> ejecutarse(List<OperacionDeEgreso> egresos, List<OperacionDeIngreso> ingresos){
        //con esto ordenamos de menor a mayor

        egresos.sort(new ComparadorOperacionesDeEgreso());
        ingresos.sort(new ComparadorOperacionesDeIngreso());

        return asignarIngresosAEgresosSiEsPosible(ingresos, egresos);
    }
}