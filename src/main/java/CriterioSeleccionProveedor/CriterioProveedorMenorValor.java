package CriterioSeleccionProveedor;

import Operaciones.OperacionDeEgreso;
import Operaciones.Presupuesto;

import java.util.Comparator;

public class CriterioProveedorMenorValor implements CriterioSeleccionProveedor {

    public Boolean validarProveedor(OperacionDeEgreso operacionDeEgreso) {
        return esProveedorDeMenorValor(operacionDeEgreso);
    }
    //TODO Duda: Como yo se cuales son todos los proveedores que hay para compararlos?
    //Los proveedores estan en cada operacion de egreso.

    public Boolean esProveedorDeMenorValor(OperacionDeEgreso operacionDeEgreso){

        Presupuesto presupuesto = operacionDeEgreso.getPresupuesto();

        return presupuesto.getMontoTotal() == operacionDeEgreso.getMontoTotal();
    }
}
