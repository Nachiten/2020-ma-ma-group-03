package domain.controllers;

import domain.entities.operaciones.MedioDePago;
import domain.entities.operaciones.TipoDocumentoComercial;
import domain.entities.operaciones.TipoMedioDePago;
import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.RepositorioDeUsuarios;
import domain.repositories.factories.FactoryRepositorio;
import domain.repositories.factories.FactoryRepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicioController {

    public InicioController(){
        this.repoTipoMedioPago = FactoryRepositorio.get(TipoMedioDePago.class);
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
    }

    private AdministradorDeSesion administradorDeSesion;

    private Repositorio<TipoMedioDePago> repoTipoMedioPago;
    private Repositorio<TipoDocumentoComercial> repoTipoDocComercial;

    /*private List<Usuario> usuarios;


    public InicioController() {
        usuarios = new ArrayList<>();
        administradorDeSesion = new AdministradorDeSesion();
    }*/

    public ModelAndView inicio(Request request, Response response) {
        //administradorDeSesion.cerrarSesion(request);
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

    public ModelAndView principal(Request request, Response response) {
        return new ModelAndView(null, "principal.hbs");
    }

    public ModelAndView ingresos(Request request, Response response) {
        return new ModelAndView(null, "ingresos.hbs");
    }

    public ModelAndView egresos(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();

        List<TipoMedioDePago> tiposMediosPago = this.repoTipoMedioPago.buscarTodos();
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();

        parametros.put("tiposMediosDePago", tiposMediosPago);
        parametros.put("tiposDocumentoComercial", tiposDocumentoComercial);

        return new ModelAndView(parametros, "egresos.hbs");
    }

    public ModelAndView presupuestos(Request request, Response response) {
        return new ModelAndView(null, "presupuestos.hbs");
    }

    public ModelAndView criterios(Request request, Response response) {
        return new ModelAndView(null, "criterios.hbs");
    }

    public ModelAndView listadoOperaciones(Request request, Response response) {
        return new ModelAndView(null, "listadoOperaciones.hbs");
    }

    public ModelAndView asociarOperacion(Request request, Response response) {
        return new ModelAndView(null, "asociarOperacion.hbs");
    }

    public ModelAndView mensajes(Request request, Response response) {
        return new ModelAndView(null, "mensajes.hbs");
    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/");
        return response;
    }
}
