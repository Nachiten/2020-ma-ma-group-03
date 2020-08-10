package Usuarios;

import Entidades.EntidadJuridica;
import Operaciones.OperacionDeEgreso;
import Operaciones.Revisor;

import java.time.LocalDateTime;
import java.util.List;

public class Usuario implements Revisor {

    private TipoUsuario tipo;
    private String nombreUsuario;
    private String contrasenia;
    private EntidadJuridica entidadJuridica;
    private BandejaDeMensajes bandejaDeMensajes;
    private List<String> contraseniasAnteriores;
    private LocalDateTime tiempoUltimaContrasenia;
    private List<OperacionDeEgreso> operacionesRevisadas;

    public Usuario(TipoUsuario tipo, String nombreUsuario, String contrasenia) {
        this.tipo = tipo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public BandejaDeMensajes getBandejaDeMensajes() {
        return bandejaDeMensajes;
    }

    public void setBandejaDeMensajes(BandejaDeMensajes bandejaDeMensajes) {
        this.bandejaDeMensajes = bandejaDeMensajes;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void actualizarRevisor(OperacionDeEgreso operacionDeEgreso){
        operacionesRevisadas.add(operacionDeEgreso);
    }

    public void publicarMensajeEnBandejaDeMensajes(String identificacion, Boolean resultadoValidacion){
        bandejaDeMensajes.publicarMensaje(resultadoValidacion, identificacion);
    }
}