package domain.controllers;

import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UsuarioController {

    private Repositorio<Usuario> respoUsuarios;

    public UsuarioController() {
        this.respoUsuarios = FactoryRepositorio.get(Usuario.class);
    }


}
