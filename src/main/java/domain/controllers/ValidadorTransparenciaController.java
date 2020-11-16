package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import validadoresContrasenia.Validador;

public class ValidadorTransparenciaController {

    private ModalAndViewController modalAndViewController;

    public ValidadorTransparenciaController(ModalAndViewController modalAndViewController){
        this.modalAndViewController = modalAndViewController;
    }

    public ModelAndView validadorTransparencia(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewValidadorTransparencia);
    }

    public ModelAndView modalAndViewValidadorTransparencia() {
        return new ModelAndView(modalAndViewController.getParametros(), "validadorTransparencia.hbs");
    }
}
