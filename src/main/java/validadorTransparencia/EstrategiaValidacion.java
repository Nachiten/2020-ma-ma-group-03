package validadorTransparencia;

import domain.entities.operaciones.OperacionDeEgreso;

public abstract class EstrategiaValidacion {

    public Boolean validarEgreso(OperacionDeEgreso egreso){return true;}
}
