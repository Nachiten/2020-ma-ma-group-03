package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;
import Operaciones.Presupuesto;

import java.util.Collections;
import java.util.Comparator;

public class ValidarCriterioSeleccionDeMenorValor extends EstrategiaValidacion {
    public boolean validarEgreso(OperacionDeEgreso egreso){
        // 1 ordenar los presupuestos de la operacion de menor a mayor | No se como ordenar si usar sort() u otra cosa
        // 2 corroborar q el primero de la lista de presupuestos ordenados (menor a mayor) sea == a el seleccionado (egreso.getPresupuestoSeleccionado()

        // Lista de prespuestos -> egreso.getPresupuestos() | Ordenar por el montoTotal para luego sacar el de menor monto


        // TODO. Hacer andar esto

        //Collections.sort(egreso.getPresupuestos().);
        //egreso.getPresupuestos().sort(egreso.getPresupuestos().getMontoTotal());
        //presupuestos: List<Presupuesto>

        return true;
    }
}
