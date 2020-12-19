package domain.controllers;

import domain.entities.usuarios.Usuario;
import spark.Request;

import java.util.HashMap;
import java.util.Optional;

public class  ContextoDeUsuarioLogueado {

    private HashMap<String, Usuario> usuarioLogueadoPorIDDeSesion;
    private AdministradorDeSesion administradorDeSesion;

    public ContextoDeUsuarioLogueado() {
        this.usuarioLogueadoPorIDDeSesion = new HashMap<>();
        this.administradorDeSesion = new AdministradorDeSesion();
    }

    public void inicioSesionNuevoUsuarioLogueado(Request request, Usuario unUsuario) {
        String idDeSesion = administradorDeSesion.iniciarSesion(request, unUsuario);
        usuarioLogueadoPorIDDeSesion.put(idDeSesion, unUsuario);
    }

    private String idDeSesionEn(Request request){
        return request.session().id();
    }

    public boolean esValidoElUsuarioLogueadoEn(Request request) {
        String idDeSesion = idDeSesionEn(request);
        return usuarioLogueadoPorIDDeSesion.containsKey(idDeSesion) && administradorDeSesion.esLaSesionValida(idDeSesion);
    }

    public void cerrarSesion(Request request) {
        String idDeSesion = idDeSesionEn(request);
        this.usuarioLogueadoPorIDDeSesion.remove(idDeSesion);
        administradorDeSesion.cerrarSesion(idDeSesion);
    }

    public Usuario getUsuarioLogueado(Request request) {
        return usuarioLogueadoPorIDDeSesion.getOrDefault(idDeSesionEn(request), null);
    }
}