package model;

import main.VinculacionService;

import java.util.List;

public class CriterioEjecucionFecha extends VinculacionService implements CriterioEjecucion {

    public List<String> ejecutarse(List<OperacionDeEgreso> egresos, List<OperacionDeIngreso> ingresos){
        //ordena de mas vieja a mas nueva!

        egresos.sort((o1, o2) -> {
            if (o1.getFecha() == null || o2.getFecha() == null)
                return 0;
            return o1.getFecha().compareTo(o2.getFecha());
        });

        ingresos.sort((o1, o2) -> {
            if (o1.getFecha() == null || o2.getFecha() == null)
                return 0;
            return o1.getFecha().compareTo(o2.getFecha());
        });
    return asignarEgresosAIngresosSiEsPosibleEnFecha(egresos, ingresos);
    }
}