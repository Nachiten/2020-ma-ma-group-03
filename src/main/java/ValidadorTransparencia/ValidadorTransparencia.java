package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;
import Usuarios.BandejaDeMensajes;
import Usuarios.Mensaje;
import Usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ValidadorTransparencia {
    private List<EstrategiaValidacion> validaciones;


    public void tratarOperacion(OperacionDeEgreso operacionDeEgreso){ //TODO cambiar nombre

        publicarMensaje(operacionDeEgreso, validarEgreso(operacionDeEgreso));
    }

    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){

        //Boolean resultado = recorrer la lista de validadores haciendo validacion.
        return true;
    }

    private void publicarMensaje(OperacionDeEgreso operacionDeEgreso,Boolean resultado){

        Mensaje mensaje = crearMensajeValidacion(resultado);
        operacionDeEgreso.getUsuario().getBandejaDeMensajes().publicarMensaje(mensaje);
    }

    public Mensaje crearMensajeValidacion(Boolean resultado){

        return Mensaje.crearResultadoValidacion(resultado);
    }
}
