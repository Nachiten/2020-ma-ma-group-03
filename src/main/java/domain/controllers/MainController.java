package domain.controllers;

import domain.entities.usuarios.TipoUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class MainController {

    private ModalAndViewController modalAndViewController;

    public MainController(ModalAndViewController modalAndViewController){
        this.modalAndViewController = modalAndViewController;
    }

    public ModelAndView principal(Request request, Response response) throws Exception {
        if(modalAndViewController.getUsuario().getTipo().equals(TipoUsuario.ESTANDAR)){
            return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> new ModelAndView(modalAndViewController.getParametros(), "inicioEstandar.hbs"));
        }
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> new ModelAndView(modalAndViewController.getParametros(), "inicioAdministrador.hbs"));

    }
}
