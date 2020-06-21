package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;

public class ValidarCriterioSeleccionProveedor extends EstrategiaValidacion {
    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getCriterioSeleccionProveedor().validarProveedor(operacionDeEgreso);
    }
}