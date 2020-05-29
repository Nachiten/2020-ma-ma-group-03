package Usuarios;

import Operaciones.OperacionDeEgreso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    public TipoUsuario tipo;
    public String user;
    public String password;

    // Lista de contrasenias anteriores
    List<String> contrasAnteriores = new ArrayList<>();

    LocalDateTime tiempoUltimaContrasenia;

    public Usuario(TipoUsuario tipo, String user, String password) {
        this.tipo = tipo;
        this.user = user;
        this.password = password;

        // Se agrega la contraseña establecida al crear el usuario. Se agregaran mas contrasenias a medida que se vayan cambiando. (rotacion)
        contrasAnteriores.add(password);

        // La fecha de la ultima contraseña es la actual en el momento de instanciacion
        tiempoUltimaContrasenia = LocalDateTime.now();
    }

    public void realizarOperacion(OperacionDeEgreso operacionDeEgreso) {

    }
}