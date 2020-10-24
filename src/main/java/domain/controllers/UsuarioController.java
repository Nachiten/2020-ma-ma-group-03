package domain.controllers;

import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class UsuarioController {

    private Repositorio<Usuario> repoUsuarios;

    public UsuarioController() {
        this.repoUsuarios = FactoryRepositorio.get(Usuario.class);
    }

    public Repositorio<Usuario> getRepoUsuarios() {
        return repoUsuarios;
    }

    public ModelAndView cerrarSesion(Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        model.put("mensaje", "¿Seguro que deseas cerrar sesión?");
        return new ModelAndView(model, "modalCerrarSesion.hbs");
    }

}
