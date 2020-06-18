package CriterioSeleccionProveedor;

import Operaciones.OperacionDeEgreso;
import Operaciones.Presupuesto;

import java.util.Comparator;

public class CriterioProveedorMenorValor implements CriterioSeleccionProveedor {

    public Boolean validarProveedor(OperacionDeEgreso operacionDeEgreso){
        return esProveedorDeMenorValor(operacionDeEgreso);
    }

    public Boolean esProveedorDeMenorValor(OperacionDeEgreso operacionDeEgreso){

        Presupuesto presupuesto = operacionDeEgreso.getPresupuesto();

        return presupuesto.getMontoTotal() == operacionDeEgreso.getMontoTotal();
    }
}
