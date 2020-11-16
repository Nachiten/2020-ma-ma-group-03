package domain.controllers;

import domain.entities.usuarios.Mensaje;
import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class MensajesController {

    private ModalAndViewController modalAndViewController;
    private Repositorio<Mensaje> repoMensajes;

    public MensajesController(ModalAndViewController modalAndViewController) {
        this.modalAndViewController = modalAndViewController;
        this.repoMensajes = FactoryRepositorio.get(Mensaje.class);
    }

    private ModelAndView modalAndViewMensajes(){
        List<Mensaje> listaMensajes = this.repoMensajes.buscarTodos();
        Usuario usuarioLogueado = this.modalAndViewController.getUsuario();
        List<Mensaje> mensajesUsuario = listaMensajes.stream().filter(mensaje -> mensaje.getUsuarioAsociado().equals(usuarioLogueado)).collect(Collectors.toList());
        modalAndViewController.getParametros().put("bandejaDeMensajes", mensajesUsuario);
        return new ModelAndView(modalAndViewController.getParametros(), "mensaje.hbs");
    }

    public ModelAndView mensajes(Request request, Response response)throws Exception {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMensajes);
    }

    public ModelAndView mostrarContenidoMensaje(Request request, Response response) {
        return new ModelAndView(null,"");
    }
}
