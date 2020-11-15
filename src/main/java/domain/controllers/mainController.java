package domain.controllers;

import domain.entities.usuarios.TipoUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class mainController {

    private ModalAndViewController modalAndViewController;

    public mainController(ModalAndViewController modalAndViewController){
        this.modalAndViewController = modalAndViewController;
    }

    public ModelAndView principal(Request request, Response response) throws Exception {

        modalAndViewController.cargarParametosHashMap();
        if(modalAndViewController.getUsuario().getTipo().equals(TipoUsuario.ESTANDAR)){
            return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> new ModelAndView(modalAndViewController.getParametros(), "inicioEstandar.hbs"));
        }
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> new ModelAndView(modalAndViewController.getParametros(), "inicioEstandar.hbs"));

    }

    private ModelAndView modalAndViewMensajes(){
        return new ModelAndView(modalAndViewController.getParametros(), "mensaje.hbs");
    }

    public ModelAndView mensajes(Request request, Response response)throws Exception {

        modalAndViewController.cargarParametosHashMap();
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewMensajes());
    }
}
