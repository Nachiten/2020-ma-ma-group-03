package domain.repositories;

import domain.entities.vendedor.Proveedor;
import domain.repositories.factories.FactoryRepositorio;

public class RepoProveedores {

    private Repositorio<Proveedor> repoProveedores;

    public RepoProveedores() {
        this.repoProveedores = FactoryRepositorio.get(Proveedor.class);
    }

    public Repositorio<Proveedor> getRepoProveedores() {
        return repoProveedores;
    }

}
