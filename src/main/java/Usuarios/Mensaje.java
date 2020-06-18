package Usuarios;

import Operaciones.OperacionDeEgreso;

import java.util.Date;

public class Mensaje {
    private Date fecha;
    private String contenido;
    private OperacionDeEgreso operacionDeEgreso;

    public Mensaje(OperacionDeEgreso operacionDeEgreso,Boolean resultado){
        fecha = new Date();
        contenido = resultado.toString();
        this.operacionDeEgreso = operacionDeEgreso;
    }
}
