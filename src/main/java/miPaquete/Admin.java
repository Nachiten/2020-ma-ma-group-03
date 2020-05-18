package miPaquete;
import java.time.LocalDateTime;

class Admin extends Usuario {

    // Contructor
    public Admin( String unaContra ){
        // Se establece la contrasenia
        password = unaContra;
        // Se agrega la nueva contraseña a las contras anteriores
        contrasAnteriores.add(unaContra);
        // La fecha de la ultima contraseña es la actual
        tiempoUltimaContrasenia = LocalDateTime.now();
    }
}
