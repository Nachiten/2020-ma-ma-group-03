package Usuarios;

import java.util.List;

public class BandejaDeMensajes {
    private List<Mensaje> mensajes;

    public void publicarMensaje(Boolean resultado){
        Mensaje mensaje = new Mensaje(resultado);
        mensajes.add(mensaje);
    }
}
