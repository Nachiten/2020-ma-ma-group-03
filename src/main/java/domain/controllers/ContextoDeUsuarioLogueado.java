package domain.controllers;

import domain.entities.usuarios.Usuario;
import spark.Request;

import java.util.Optional;

public class  ContextoDeUsuarioLogueado {

    private Optional<Usuario> usuarioLogueadoOpcional;
    private AdministradorDeSesion administradorDeSesion;

    public ContextoDeUsuarioLogueado() {
        this.usuarioLogueadoOpcional = Optional.empty();
        this.administradorDeSesion = new AdministradorDeSesion();
    }

    public void inicioSesionNuevoUsuarioLogueado(Request request, Usuario unUsuario) {
        usuarioLogueadoOpcional = Optional.of(unUsuario);
        administradorDeSesion.iniciarSesion(request, unUsuario);
    }

    public boolean esValidoElUsuarioLogueadoEn(Request request) {
        return usuarioLogueadoOpcional.isPresent() && administradorDeSesion.esLaSesionValida(request);
    }

    public void cerrarSesion(Request request) {
        this.usuarioLogueadoOpcional = Optional.empty();
        administradorDeSesion.cerrarSesion(request);
    }

    public Usuario getUsuarioLogueado() throws Exception {
        return usuarioLogueadoOpcional.orElseThrow(() -> new Exception("No se encuentra el usuario logueado..."));
    }
}