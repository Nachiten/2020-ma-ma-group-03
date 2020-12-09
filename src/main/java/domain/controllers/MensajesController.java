package domain.controllers;

import domain.entities.usuarios.Mensaje;
import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;

public class MensajesController {

    private ModalAndViewController modalAndViewController;
    private Repositorio<Mensaje> repoMensajes;

    public MensajesController(ModalAndViewController modalAndViewController) {
        this.modalAndViewController = modalAndViewController;
        this.repoMensajes = FactoryRepositorio.get(Mensaje.class);
    }

    private ModelAndView modalAndViewMensajes(){
        Usuario usuarioLogueado = this.modalAndViewController.getUsuario();
        List<Mensaje> mensajesUsuario = usuarioLogueado.getBandejaDeMensajes();

        modalAndViewController.getParametros().put("bandejaDeMensajes", mensajesUsuario);
        return new ModelAndView(modalAndViewController.getParametros(), "mensaje.hbs");
    }

    public ModelAndView mensajes(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMensajes);
    }

    public ModelAndView mostrarContenidoMensaje(Request request, Response response) {
        String id = request.params("id");
        int idMensaje = Integer.parseInt(id);
        Mensaje mensaje = repoMensajes.buscar(idMensaje);
        modalAndViewController.getParametros().put("contenidoMensaje", mensaje.getContenido());
        mensaje.leerMensaje();
        repoMensajes.modificar(mensaje);
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo3.hbs");
    }
}
