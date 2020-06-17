package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;

public class ValidarCriterioSeleccionDeMenorValor extends EstrategiaValidacion {
    public boolean validarEgreso(OperacionDeEgreso egreso){
        return true;
    }
}
