package miPaquete;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// IDEA PARA LOS USUARIOS | NO IMPLEMENTADA EN NINGUN LADO By: Nachiten
public abstract class Usuario {

    String user; // Para mas adelante
    String password;
    TipoUsuario tipo;

    // Se inicia poniendo la contraseña establecida al crear el usuario. Se agregaran mas contrasenias a medida que se vayan cambiando.
    List<String> contrasAnteriores = new ArrayList<>();

    // Este tiempo se establece al crear el usuario en la hora actual del sistema (la hora en la que se creó el usuario)
    LocalDateTime tiempoUltimaContrasenia;

    // Contructor
    public Usuario( String unaContra , TipoUsuario unTipo){
        // Se establece la contrasenia
        password = unaContra;
        // Se agrega la nueva contraseña a las contras anteriores
        contrasAnteriores.add(unaContra);
        // La fecha de la ultima contraseña es la actual
        tiempoUltimaContrasenia = LocalDateTime.now();
        tipo = unTipo;
    }
}