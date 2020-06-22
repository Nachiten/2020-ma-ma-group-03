package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;

public class ValidarCantidadPresupuestos extends EstrategiaValidacion {

    @Override
    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getListaPresupuestos().size() == operacionDeEgreso.getCantidadPresupuestosRequerida();
    }
}