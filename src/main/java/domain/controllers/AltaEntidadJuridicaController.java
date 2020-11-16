package domain.controllers;

import domain.entities.entidades.EntidadJuridica;
import domain.entities.usuarios.Usuario;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class AltaEntidadJuridicaController {

    private ContextoDeUsuarioLogueado contextoDeUsuarioLogueado;
    private Usuario usuario ;
    private Map<String, Object> parametros;

    public AltaEntidadJuridicaController(ContextoDeUsuarioLogueado contextoDeUsuarioLogueado) {
        this.contextoDeUsuarioLogueado = contextoDeUsuarioLogueado;
        this.parametros = new HashMap<>();
        this.usuario = new Usuario();
    }

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

    private ModelAndView modalAndViewAltaEntidadJuridica(){
        return new ModelAndView(parametros,"altaEntidadJuridica.hbs");
    }

    public ModelAndView mostrarPaginaAltaEntidadJuridica(Request request, Response response) throws Exception {
        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewAltaEntidadJuridica());
    }

    /*
    public ModelAndView guardarEntidadJuridica(Request request, Response response){


    }
*/


}
