package Usuarios;

import Operaciones.OperacionDeEgreso;

import java.util.List;

public class BandejaDeMensajes {
    private List<Mensaje> mensajes;

    public void publicarMensaje(OperacionDeEgreso operacionDeEgreso, Boolean resultado){
        Mensaje mensaje = new Mensaje(operacionDeEgreso, resultado);
        mensajes.add(mensaje);
    }
}
