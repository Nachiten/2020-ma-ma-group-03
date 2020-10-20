package domain.controllers;

import domain.entities.usuarios.Usuario;
import domain.repositories.RepositorioDeUsuarios;
import domain.repositories.factories.FactoryRepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicioController {

    private AdministradorDeSesion administradorDeSesion;
    /*private List<Usuario> usuarios;


    public InicioController() {
        usuarios = new ArrayList<>();
        administradorDeSesion = new AdministradorDeSesion();
    }*/

    public ModelAndView inicio(Request request, Response response) {
        administradorDeSesion.cerrarSesion(request);
        return new ModelAndView(null, "login.hbs");
    }
/*
    public ModelAndView inicio(Request request, Response response){
        Map<String, Object> parametros = new HashMap<>();
        return new ModelAndView(parametros,"login.hbs");
    }*/
    public Response login(Request request, Response response){
        try{
            RepositorioDeUsuarios repoUsuarios = FactoryRepositorioUsuarios.get();

            String nombreDeUsuario = request.queryParams("nombreDeUsuario");
            String contrasenia     = request.queryParams("contrasenia");

            if(repoUsuarios.existe(nombreDeUsuario, contrasenia)){
                Usuario usuario = repoUsuarios.buscarUsuario(nombreDeUsuario, contrasenia);

                request.session(true);
                request.session().attribute("id", usuario.getId());

                response.redirect("/usuarios");
            }
            else{
                response.redirect("/");
            }
        }
        catch (Exception e){
            //Funcionalidad disponible solo con persistencia en Base de Datos
            response.redirect("/usuarios");
        }
        finally {
            return response;
        }
    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/");
        return response;
    }
}
