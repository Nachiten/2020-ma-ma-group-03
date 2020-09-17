package Usuarios;

import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "bandejaMensajes")
public class BandejaDeMensajes extends EntidadPersistente{

    @OneToMany (cascade = {CascadeType.ALL})
    @JoinColumn(name = "bandejaMensajes_id", referencedColumnName = "id")
    private List<Mensaje> mensajes;

    //private Usuario usuarioAsociado;

    public BandejaDeMensajes(){
        mensajes = new ArrayList<>();
        //this.usuarioAsociado = usuarioAsociado;
    }

    public void publicarMensaje(Boolean resultado, String identificacion){
        Mensaje mensaje = new Mensaje(resultado, identificacion);
        mensajes.add(mensaje);
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }
}
