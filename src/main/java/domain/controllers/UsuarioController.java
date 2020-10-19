package domain.controllers;

import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;

public class UsuarioController {

    private Repositorio<Usuario> respoUsuarios;

    public UsuarioController() {
        this.respoUsuarios = FactoryRepositorio.get(Usuario.class);
    }
}
