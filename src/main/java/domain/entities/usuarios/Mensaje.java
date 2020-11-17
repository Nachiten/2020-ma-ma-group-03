package domain.entities.usuarios;

import persistencia.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "mensaje")
public class Mensaje extends EntidadPersistente {

    @Column(name = "nombreUsuarioAsociado")
    private String nombreUsuarioAsociado;

    @Column(name = "fechaCreacion")
    private Date fechaCreado;

    @Column(name = "fechaLectura")
    private LocalDateTime fechaYHoraLeido;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "leido")
    private boolean leido;

    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Mensaje() {
        inicializar();
    }

    public Mensaje(Boolean resultado, String identificacion, Usuario usuario){
        this.fechaCreado = new Date();
        this.contenido = "La operacion de egreso: " + identificacion + " tiene resultado " + resultado.toString();
        this.nombreUsuarioAsociado = usuario.getNombre();
        inicializar();
    }

    //-------------------------------------------------------------------------
                            //METODOS
    //-------------------------------------------------------------------------

    public void leerMensaje(){
        this.fechaYHoraLeido = LocalDateTime.now();
        this.leido = true;
    }

    private void inicializar(){
        this.leido = false;
    }

    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------

    public String getNombreUsuarioAsociado() { return nombreUsuarioAsociado; }

    public String getContenido() {
        return contenido;
    }

    public Date getFechaCreado() { return fechaCreado; }

    public LocalDateTime getFechaYHoraLeido() { return fechaYHoraLeido; }
}
