package Usuarios;

import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "mensaje")
public class Mensaje extends EntidadPersistente {

    @ManyToOne (optional = false)
    @JoinColumn(name = "usuarioAsociado_id")
    private Usuario usuarioAsociado;

    @Column(name = "fechaCreacion")
    private Date fechaCreado;

    @Column(name = "fechaLectura")
    private LocalDateTime fechaYHoraLeido;

    @Column(name = "contenido")
    private String contenido;

    //CONSTRUCTOR
    public Mensaje() {
    }

    public Mensaje(Boolean resultado, String identificacion, Usuario usuario){
        this.fechaCreado = new Date();
        this.contenido = "La operacion de egreso: " + identificacion + " tiene resultado " + resultado.toString();
        this.usuarioAsociado = usuario;
    }

    //METHOD'S
    public Mensaje leerMensaje(){
        fechaYHoraLeido = LocalDateTime.now();
        return this;
    }

    //GETTERS
    public Usuario getUsuarioAsociado() { return usuarioAsociado; }

    public String getContenido() {
        return contenido;
    }

    public Date getFechaCreado() { return fechaCreado; }

    public LocalDateTime getFechaYHoraLeido() { return fechaYHoraLeido; }
}
