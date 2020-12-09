package domain.entities.usuarios;

public class Publicador {

    public void publicarMensaje(String identificacion, Usuario usuario){

        Mensaje mensaje = new Mensaje(identificacion, usuario);
        usuario.asociarMensaje(mensaje);
    }


}
