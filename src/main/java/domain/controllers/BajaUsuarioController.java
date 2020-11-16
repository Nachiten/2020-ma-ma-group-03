package domain.controllers;


import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BajaUsuarioController {

    private Repositorio<Usuario> repoUsuario;
    private Usuario usuario;
    private Map<String, Object> parametros;
    private ModalAndViewController modalAndViewController;

    public BajaUsuarioController(ModalAndViewController modalAndViewController) {

        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
        this.usuario = new Usuario();
        this.parametros = new HashMap<>();
        this.modalAndViewController = modalAndViewController;
    }

    public ModelAndView modalAndViewListarUsuarios(){

        List<Usuario> usuarios = this.repoUsuario.buscarTodos();
        List<Usuario> usuariosHabilitados = usuarios.stream().filter(usuario -> usuario.getEstoyHabilitado() & !usuario.getNombreUsuario().equals(this.usuario.getNombreUsuario())).collect(Collectors.toList());
        parametros.put("listadoDeUsuarios", usuariosHabilitados);
        return new ModelAndView(parametros, "bajaUsuario.hbs");
    }

    public ModelAndView listarUsuarios(Request request, Response response) throws Exception {

        modalAndViewController.cargarParametrosHashMap();
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewListarUsuarios());
    }

    public ModelAndView eliminar(Request request, Response response) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuario.buscar(idBuscado);

        usuarioBuscado.cambiarAInhabilitado();

        this.repoUsuario.modificar(usuarioBuscado);

        parametros.put("mensaje","El usuario se eliminó correctamente");
        return new ModelAndView(parametros,"modalInformativo2.hbs") ;
    }
}
