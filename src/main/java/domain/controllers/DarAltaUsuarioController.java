package domain.controllers;

import domain.entities.usuarios.TipoUsuario;
import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.function.Supplier;

public class DarAltaUsuarioController {

    private ContextoDeUsuarioLogueado contextoDeUsuarioLogueado;
    private Usuario usuario;
    private Map<String, Object> parametros;

    public DarAltaUsuarioController(ContextoDeUsuarioLogueado contextoDeUsuarioLogueado) {
        this.contextoDeUsuarioLogueado = contextoDeUsuarioLogueado;
        this.usuario = new Usuario();
        this.parametros = new HashMap<>();
    }

    //Evalua si se accedio correctame (previo inicio de sesion) y devuelve lo que corresponde
    //si se accedió a la página sin haberse logueado devuelve error 404
    private ModelAndView siElUsuarioEstaLogueadoRealiza(Request request, Supplier<ModelAndView> bloque){

        if(!contextoDeUsuarioLogueado.esValidoElUsuarioLogueadoEn(request)){
            return new ModelAndView(null,"error404.hbs");
        }

        return bloque.get();
    }

    private void cargarParametosHashMap() throws Exception {
        usuario = contextoDeUsuarioLogueado.getUsuarioLogueado();
        parametros.put("nombre", usuario.getNombre());
        parametros.put("apellido", usuario.getApellido());
    }

    //devuelve la página correspondiente
    private ModelAndView modalAndViewAltaUsuario(){
        parametros.put("tiposUsuarios",TipoUsuario.values());
        return new ModelAndView(parametros,"altaUsuario.hbs");
    }

    public ModelAndView altaUsuario(Request request, Response response) throws Exception {
        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewAltaUsuario());
    }


}
