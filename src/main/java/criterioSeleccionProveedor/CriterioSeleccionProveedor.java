package criterioSeleccionProveedor;

import domain.entities.operaciones.OperacionDeEgreso;

public interface CriterioSeleccionProveedor {

    Boolean seleccionarProveedor(OperacionDeEgreso operacionDeEgreso);
}
