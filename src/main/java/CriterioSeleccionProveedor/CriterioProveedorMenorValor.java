package CriterioSeleccionProveedor;

import Operaciones.OperacionDeEgreso;
import Operaciones.Presupuesto;

import java.util.Comparator;
import java.util.List;

public class CriterioProveedorMenorValor implements CriterioSeleccionProveedor {

    public Boolean validarProveedor(OperacionDeEgreso operacionDeEgreso){
        return esProveedorDeMenorValor(operacionDeEgreso);
    }

    public Boolean esProveedorDeMenorValor(OperacionDeEgreso operacionDeEgreso){

        List<Presupuesto> presupuestos = operacionDeEgreso.getPresupuestos();
        Comparator<Presupuesto> comparator = Comparator.comparing(Presupuesto::getMontoTotal);

        return presupuestos.stream().min(comparator).get().getMontoTotal() == operacionDeEgreso.getMontoTotal();
    }
}
