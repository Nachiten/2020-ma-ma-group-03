package model;

import java.util.List;

public interface CriterioEjecucion {

    List<String> ejecutarse(List<OperacionDeEgreso> egresos, List<OperacionDeIngreso> ingresos);
}