package Usuarios;

public class Publicador {

    public void publicarMensaje(Boolean resultado, String identificacion, Usuario usuario){

        Mensaje mensaje = new Mensaje(resultado, identificacion, usuario);
        usuario.asociarMensaje(mensaje);
    }


}
