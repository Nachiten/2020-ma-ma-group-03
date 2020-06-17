package CriterioSeleccionProveedor;

import Operaciones.OperacionDeEgreso;
import Operaciones.Presupuesto;

import java.util.Comparator;
import java.util.List;

public class CriterioProveedorMenorValor extends CriterioSeleccionProveedor {

    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        return seleccionarProveedorDeMenorValor(operacionDeEgreso);
    }

    public Boolean seleccionarProveedorDeMenorValor(OperacionDeEgreso operacionDeEgreso){

        List<Presupuesto> presupuestos = operacionDeEgreso.getPresupuestos();
        Comparator<Presupuesto> comparator = Comparator.comparing(Presupuesto::getMontoTotal);

        return presupuestos.stream().min(comparator).get().getMontoTotal() == operacionDeEgreso.getMontoTotal();
    }
}
