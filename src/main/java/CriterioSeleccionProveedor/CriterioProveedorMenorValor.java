package CriterioSeleccionProveedor;

import Operaciones.OperacionDeEgreso;
import Operaciones.Presupuesto;

import java.util.Comparator;
import java.util.Optional;

public class CriterioProveedorMenorValor implements CriterioSeleccionProveedor {

    public Boolean seleccionarProveedor(OperacionDeEgreso operacionDeEgreso) {
        return esPresupuestoDeMenorValor(operacionDeEgreso);
    }

    public Boolean esPresupuestoDeMenorValor(OperacionDeEgreso operacionDeEgreso){

        Optional<Presupuesto> presupuestoMenorValor = operacionDeEgreso.getPresupuestos().stream().min(Comparator.comparing(Presupuesto::getMontoTotal));

        return presupuestoMenorValor.isPresent() && presupuestoMenorValor.get().getMontoTotal() == operacionDeEgreso.getMontoTotal();

    }
}
