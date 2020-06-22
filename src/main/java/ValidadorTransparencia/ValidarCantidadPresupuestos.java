package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;

public class ValidarCantidadPresupuestos extends EstrategiaValidacion {

    @Override
    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getPresupuestos().size() == operacionDeEgreso.getCantidadPresupuestosRequerida();
    }
}