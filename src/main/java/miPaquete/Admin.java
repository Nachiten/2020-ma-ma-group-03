package miPaquete;

import java.time.LocalDateTime;

class Admin extends Usuario {

    // Contructor
    public Admin( String unaContra ){
        password = unaContra;
        contrasAnteriores.add(unaContra);
        tiempoUltimaContrasenia = LocalDateTime.now();
    }

}