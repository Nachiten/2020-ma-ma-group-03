package CriterioSeleccionProveedor;

import Operaciones.OperacionDeEgreso;

public interface CriterioSeleccionProveedor {

    Boolean validarProveedor(OperacionDeEgreso operacionDeEgreso);
}
