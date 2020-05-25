package Model.Usuarios;

import Model.Operaciones.OperacionDeEgreso;

public class Usuario {

    public TipoUsuario tipo;
    public String user;
    public String password;

    public Usuario(TipoUsuario tipo, String user, String password) {
        this.tipo = tipo;
        this.user = user;
        this.password = password;
    }

    public void realizarOperacion(OperacionDeEgreso operacionDeEgreso) {
        // TODO implement here
    }
}