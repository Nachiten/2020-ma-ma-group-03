package CriterioSeleccionProveedor;

import Operaciones.OperacionDeEgreso;
import Operaciones.Presupuesto;

public class CriterioProveedorMenorValor implements CriterioSeleccionProveedor {

    public Boolean validarProveedor(OperacionDeEgreso operacionDeEgreso) {
        return esPresupuestoDeMenorValor(operacionDeEgreso);
    }

    public Boolean esPresupuestoDeMenorValor(OperacionDeEgreso operacionDeEgreso){

        //Presupuesto presupuesto = operacionDeEgreso.getPresupuesto();

        //return presupuesto.getMontoTotal() == operacionDeEgreso.getMontoTotal();
        return false;
        //TODO Sacar el minimo de la lista y comprar que el monto sea ese
    }
}
