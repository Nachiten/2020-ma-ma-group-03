package Operaciones;

import Usuarios.Usuario;

public interface GestorDeRevisores {

    void agregarRevisor(Usuario revisor);
    void removerRevisor(Usuario revisor);
    void notificarRevisor();
}
