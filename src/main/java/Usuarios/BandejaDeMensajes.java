package Usuarios;

import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table (name = "bandejaMensajes")
public class BandejaDeMensajes {

    //@OneToMany (cascade = {CascadeType.ALL}) | OLD
    //@JoinColumn(name = "bandejaMensajes_id", referencedColumnName = "id")
    private List<Mensaje> mensajes;

    private Usuario usuarioAsociado;

    public BandejaDeMensajes(Usuario usuarioAsociado){
        mensajes = new ArrayList<>();
    }

    public void publicarMensaje(Boolean resultado, String identificacion){
        Mensaje mensaje = new Mensaje(resultado, identificacion, this.usuarioAsociado);
        mensajes.add(mensaje);
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }
}
