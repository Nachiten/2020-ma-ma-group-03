package miPaquete;

import java.time.LocalDateTime;

class Estandar extends Usuario {

    // Contructor
    public Estandar( String unaContra ){
        password = unaContra;
        contrasAnteriores.add(unaContra);
        tiempoUltimaContrasenia = LocalDateTime.now();
    }

}