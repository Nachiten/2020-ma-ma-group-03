package domain.controllers;


import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class BajaUsuarioController {

    private Repositorio<Usuario> repoUsuario;
    private ModalAndViewController modalAndViewController;


    public BajaUsuarioController(ModalAndViewController modalAndViewController) {

        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
        this.modalAndViewController = modalAndViewController;
    }

    private ModelAndView modalAndViewListarUsuarios(){

        List<Usuario> usuarios = this.repoUsuario.buscarTodos();
        List<Usuario> usuariosHabilitados = usuarios.stream().filter(usuario -> usuario.getEstoyHabilitado() & !usuario.getNombreUsuario().equals(modalAndViewController.getUsuario().getNombreUsuario())).collect(Collectors.toList());
        modalAndViewController.getParametros().put("listadoDeUsuarios", usuariosHabilitados);
        return new ModelAndView(modalAndViewController.getParametros(), "bajaUsuario.hbs");
    }

    public ModelAndView listarUsuarios(Request request, Response response) throws Exception {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewListarUsuarios);
    }

    public ModelAndView eliminar(Request request, Response response) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuario.buscar(idBuscado);
        usuarioBuscado.cambiarAInhabilitado();
        this.repoUsuario.modificar(usuarioBuscado);
        modalAndViewController.getParametros().put("mensaje","El usuario se dio de baja correctamente");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs") ;
    }
}
