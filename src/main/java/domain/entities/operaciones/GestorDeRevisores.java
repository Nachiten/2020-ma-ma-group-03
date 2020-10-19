package domain.entities.operaciones;

import domain.entities.usuarios.Usuario;

public interface GestorDeRevisores {

    void agregarRevisor(Usuario revisor);
    void removerRevisor(Usuario revisor);
    void notificarRevisor();
}
