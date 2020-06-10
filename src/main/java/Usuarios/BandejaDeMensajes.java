package Usuarios;

import java.util.List;

public class BandejaDeMensajes {
    private List<Mensaje> mensajes;

    public void publicarMensaje(Mensaje mensaje){
        mensajes.add(mensaje);
    }
}
