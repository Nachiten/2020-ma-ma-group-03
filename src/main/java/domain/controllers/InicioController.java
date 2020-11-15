package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class InicioController {

    private ContextoDeUsuarioLogueado contextoDeUsuarioLogueado;

    public InicioController(ContextoDeUsuarioLogueado contextoDeUsuarioLogueado){
        this.contextoDeUsuarioLogueado = contextoDeUsuarioLogueado;
    }

    //Devuelve la pantalla inicial (login) y cierra la sesión que se encuentra abierta
    public ModelAndView inicio(Request request, Response response) {
        contextoDeUsuarioLogueado.cerrarSesion(request);
        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView retornarError(Request request, Response response){
        return new ModelAndView(null,"error404.hbs");
    }
}
