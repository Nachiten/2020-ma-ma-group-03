package domain.repositories;

import domain.entities.usuarios.Usuario;
import domain.repositories.factories.FactoryRepositorio;

public class RepoUsuarios {

    private Repositorio<Usuario> repoUsuarios;

    public RepoUsuarios() {
        this.repoUsuarios = FactoryRepositorio.get(Usuario.class);
    }

    public Repositorio<Usuario> getRepoUsuarios() {
        return repoUsuarios;
    }
}
