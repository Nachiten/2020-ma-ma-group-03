package Usuarios;

import java.util.Date;

public class Mensaje {
    private Date fecha;
    private String contenido;

    public Mensaje(Boolean resultado){
        fecha = new Date();
        contenido = resultado.toString();
    }
}
