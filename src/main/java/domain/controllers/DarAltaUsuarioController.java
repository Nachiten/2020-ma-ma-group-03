package domain.controllers;

import domain.entities.usuarios.TipoUsuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class DarAltaUsuarioController {



    public DarAltaUsuarioController() {

    }

    public ModelAndView altaUsuario(Request request, Response response){
        Map<String,Object> model = new HashMap<>();
        model.put("tiposUsuarios",TipoUsuario.values());
        return new ModelAndView(model,"altaUsuario.hbs");
    }


}
