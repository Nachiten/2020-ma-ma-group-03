package miPaquete;
import java.time.LocalDateTime;

class Admin extends Usuario {
    // Contructor
    public Admin( String unaContra ){
            password = unaContra;

            // La fecha de la ultima contraseña es la actual
            contrasAnteriores = new String[5];
            contrasAnteriores.add(unaContra);
            tiempoUltimaContrasenia = LocalDateTime.now();
        }
    }
}