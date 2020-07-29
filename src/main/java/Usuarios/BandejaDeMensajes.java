package Usuarios;

import java.util.ArrayList;
import java.util.List;

public class BandejaDeMensajes {
    private List<Mensaje> mensajes;

    public BandejaDeMensajes(){
        mensajes = new ArrayList<>();
    }

    public void publicarMensaje(Boolean resultado, String identificacion){
        Mensaje mensaje = new Mensaje(resultado, identificacion);
        mensajes.add(mensaje);
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }
}
