package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;
import Usuarios.Mensaje;
import java.util.List;

public class ValidadorTransparencia {
    private List<EstrategiaValidacion> validaciones;

    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        return validaciones.stream().allMatch(unaValidacion -> unaValidacion.validarEgreso(operacionDeEgreso));
    }

    private void publicarMensaje(OperacionDeEgreso operacionDeEgreso, Boolean resultado){
        operacionDeEgreso.getUsuario().getBandejaDeMensajes().publicarMensaje(new Mensaje(resultado));
    }

}
