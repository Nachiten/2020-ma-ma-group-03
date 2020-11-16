package domain.controllers;

import domain.entities.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class AltaEntidadJuridicaController {

    private Usuario usuario ;
    private Map<String, Object> parametros;
    private ModalAndViewController modalAndViewController;

    public AltaEntidadJuridicaController(ModalAndViewController modalAndViewController) {
        this.parametros = new HashMap<>();
        this.usuario = new Usuario();
        this.modalAndViewController = modalAndViewController;
    }

    private ModelAndView modalAndViewAltaEntidadJuridica(){
        return new ModelAndView(parametros,"altaEntidadJuridica.hbs");
    }

    public ModelAndView mostrarPaginaAltaEntidadJuridica(Request request, Response response) {
        modalAndViewController.cargarParametrosHashMap();
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewAltaEntidadJuridica());
    }

    /*
    public ModelAndView guardarEntidadJuridica(Request request, Response response){


    }
*/


}
