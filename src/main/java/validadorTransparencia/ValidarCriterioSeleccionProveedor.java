package validadorTransparencia;

import domain.entities.operaciones.OperacionDeEgreso;

public class ValidarCriterioSeleccionProveedor extends EstrategiaValidacion {
    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getCriterioSeleccionProveedor().seleccionarProveedor(operacionDeEgreso);
    }
}
