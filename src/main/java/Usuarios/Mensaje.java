package Usuarios;

import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "mensaje")
public class Mensaje extends EntidadPersistente {

    // TODO | Ver referencia del mensaje hacia el usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuarioAsociado;

    @Column(name = "fechaCreacion")
    private Date fechaCreado;
    @Column(name = "fechaLectura")
    private LocalDateTime fechaYHoraLeido;
    @Column(name = "contenido")
    private String contenido;

    public Mensaje(Boolean resultado, String identificacion, Usuario usuario){
        this.fechaCreado = new Date();
        this.contenido = "La operacion de egreso: " + identificacion + " tiene resultado " + resultado.toString();
        this.usuarioAsociado = usuario;
    }

    public Mensaje leerMensaje(){
        fechaYHoraLeido = LocalDateTime.now();
        return this;
    }

    public String getContenido() {
        return contenido;
    }
}
