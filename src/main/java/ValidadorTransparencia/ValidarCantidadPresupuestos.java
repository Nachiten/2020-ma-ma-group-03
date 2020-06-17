package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;

public class ValidarCantidadPresupuestos extends EstrategiaValidacion {

    @Override
    public boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getPresupuestos().size() >= 1;
    }
}