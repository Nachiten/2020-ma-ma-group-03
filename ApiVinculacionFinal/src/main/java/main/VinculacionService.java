package main;

import model.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class VinculacionService extends Validacion {

    public Boolean asignarEgresoAIngresoSiEsPosible(OperacionDeEgreso operacionDeEgreso, OperacionDeIngreso operacionDeIngreso) {
        if (puedenVincularse(operacionDeIngreso, operacionDeEgreso)) {
            asociarEgresoConIngreso(operacionDeIngreso, operacionDeEgreso);
            return true;
        }
        return false;
    }

    public List<String> asignarEgresosAIngresosSiEsPosible(List<OperacionDeEgreso> egresos, List<OperacionDeIngreso> ingresos) {
        for (OperacionDeEgreso operacionDeEgreso : egresos) {
            for (OperacionDeIngreso operacionDeIngreso : ingresos) {
                Boolean resultadoAsignacion = asignarEgresoAIngresoSiEsPosible(operacionDeEgreso, operacionDeIngreso);
                if (resultadoAsignacion) {
                    break;
                }
            }
        }
        GsonConverter gsonConverter = new GsonConverter();
        return gsonConverter.convertirIngresosAGson(ingresos);
    }

    public List<String> asignarIngresosAEgresosSiEsPosible(List<OperacionDeIngreso> ingresos, List<OperacionDeEgreso> egresos) {
        for (OperacionDeIngreso operacionDeIngreso : ingresos) {
            for (OperacionDeEgreso operacionDeEgreso : egresos) {
                asignarEgresoAIngresoSiEsPosible(operacionDeEgreso, operacionDeIngreso);
            }
        }
        GsonConverter gsonConverter = new GsonConverter();
        return gsonConverter.convertirIngresosAGson(ingresos);
    }

    public List<String> asignarEgresosAIngresosSiEsPosibleEnFecha(List<OperacionDeEgreso> egresos, List<OperacionDeIngreso> ingresos) {
        for (OperacionDeEgreso operacionDeEgreso : egresos) {
            for (OperacionDeIngreso operacionDeIngreso : ingresos) {
                Boolean resultadoAsignacion = asignarEgresoAIngresoSiEsPosibleEnFecha(operacionDeEgreso, operacionDeIngreso);
                if (resultadoAsignacion) {
                    break;
                }
            }
        }
        GsonConverter gsonConverter = new GsonConverter();
        return gsonConverter.convertirIngresosAGson(ingresos);
    }

    public Boolean asignarEgresoAIngresoSiEsPosibleEnFecha(OperacionDeEgreso operacionDeEgreso, OperacionDeIngreso operacionDeIngreso) {
        ValidacionPeriodo validacionPeriodo = new ValidacionPeriodo();
        if (validacionPeriodo.puedenVincularse(operacionDeIngreso, operacionDeEgreso)) {
            asociarEgresoConIngreso(operacionDeIngreso, operacionDeEgreso);
            return true;
        }
        return false;
    }

    public void asociarEgresoConIngreso(OperacionDeIngreso operacionDeIngreso, OperacionDeEgreso operacionDeEgreso){
        operacionDeEgreso.asociarOperacionDeIngreso(operacionDeIngreso);
        operacionDeIngreso.asociarEgreso(operacionDeEgreso);
        System.out.println("Se asoció a la operacion de egreso:");
        System.out.print(operacionDeEgreso.getIDOperacion());
        System.out.println("\ncon la operación de ingreso:");
        System.out.println(operacionDeIngreso.getIDOperacion());
        System.out.println("\n");
    }
}