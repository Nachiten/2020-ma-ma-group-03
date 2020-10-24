package domain.controllers;

import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UsuarioController {

    private Repositorio<Usuario> repoUsuarios;

    public UsuarioController() {
        this.repoUsuarios = FactoryRepositorio.get(Usuario.class);
    }

    public Repositorio<Usuario> getRepoUsuarios() {
        return repoUsuarios;
    }


}
