package Usuarios;

import java.time.LocalDateTime;
import java.util.Date;

public class Mensaje {
    private Date fechaCreado;
    private LocalDateTime horaLeido;
    private Date fechaLeido;
    private String contenido;

    public Mensaje(Boolean resultado){
        fechaCreado = new Date();
        contenido = resultado.toString();
    }
}
