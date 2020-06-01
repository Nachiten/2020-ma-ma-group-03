package Usuarios;

import Entidades.EntidadJuridica;
import Operaciones.OperacionDeEgreso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private TipoUsuario tipo;
    private String user;
    public String password;
    private EntidadJuridica Entidad;

    // Lista de contrasenias anteriores
    private List<String> contrasAnteriores = new ArrayList<>();

    private LocalDateTime tiempoUltimaContrasenia;

    public Usuario(TipoUsuario tipo, String user, String password) {
        this.tipo = tipo;
        this.user = user;
        this.password = password;

        // Se agrega la contraseña establecida al crear el usuario. Se agregaran mas contrasenias a medida que se vayan cambiando. (rotacion)
        contrasAnteriores.add(password);

        // La fecha de la ultima contraseña es la actual en el momento de instanciacion
        tiempoUltimaContrasenia = LocalDateTime.now();
    }

    public void realizarOperacion(EntidadJuridica Entidad,OperacionDeEgreso operacionDeEgreso) {
    }
}