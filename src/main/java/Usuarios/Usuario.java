package Usuarios;

import Entidades.EntidadJuridica;
import Operaciones.OperacionDeEgreso;

import java.time.LocalDateTime;
import java.util.List;

public class Usuario {

    private TipoUsuario tipo;
    private String nombreUsuario;
    public String contrasenia;
    private EntidadJuridica entidad;
    BandejaDeMensajes bandejaDeMensajes;
    private List<String> contraseniasAnteriores;
    private LocalDateTime tiempoUltimaContrasenia;

    public Usuario(TipoUsuario tipo, String nombreUsuario, String contrasenia) {
        this.tipo = tipo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public void realizarOperacion(EntidadJuridica Entidad,OperacionDeEgreso operacionDeEgreso) {
    }
}