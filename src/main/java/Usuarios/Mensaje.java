package Usuarios;

import java.time.LocalDateTime;
import java.util.Date;

public class Mensaje {
    private Date fechaCreado;
    private LocalDateTime horaLeido;
    private Date fechaLeido;
    private String contenido;

    public Mensaje(Boolean resultado, String identificacion){
        fechaCreado = new Date();
        contenido = "La operacion de egreso: " + identificacion + " tiene resultado " + resultado.toString();
    }

    public Mensaje leerMensaje(){
        fechaLeido = new Date();
        horaLeido = LocalDateTime.now();
        return this;
    }

    public String getContenido() {
        return contenido;
    }
}
