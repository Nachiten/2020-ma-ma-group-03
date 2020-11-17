package model;

import main.VinculacionService;

import java.util.List;

public class CriterioEjecucionPrimeroEgreso extends VinculacionService implements CriterioEjecucion {

    public List<String> ejecutarse(List<OperacionDeEgreso> egresos, List<OperacionDeIngreso> ingresos){
        //se ordenan de menor a mayor!

        egresos.sort(new ComparadorOperacionesDeEgreso());
        ingresos.sort(new ComparadorOperacionesDeIngreso());

        return asignarEgresosAIngresosSiEsPosible(egresos, ingresos);
    }
}