package miPaquete;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// IDEA PARA LOS USUARIOS | NO IMPLEMENTADA EN NINGUN LADO By: Nachiten
public abstract class Usuario {

    String[] contrasAnteriores;
    LocalDateTime tiempoUltimaContrasenia; //

    String user; // Para mas adelante
    String password;

    // Se inicia poniendo la contraseña establecida al crear el usuario. Se agregaran mas contrasenias a medida que se vayan cambiando.
    List<String> contrasAnteriores = new ArrayList<String>();

    // Este tiempo se establece al crear el usuario en la hora actual del sistema (la hora en la que se creó el usuario)
    LocalDateTime tiempoUltimaContrasenia;


}