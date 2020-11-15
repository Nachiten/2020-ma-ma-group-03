package domain.controllers;

import domain.entities.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModalAndViewController {

    private Map<String, Object> parametros;
    private Usuario usuario;

    private ContextoDeUsuarioLogueado contextoDeUsuarioLogueado;

    public ModalAndViewController(ContextoDeUsuarioLogueado contextoDeUsuarioLogueado){
        this.parametros = new HashMap<>();
        this.usuario = new Usuario();

        this.contextoDeUsuarioLogueado = contextoDeUsuarioLogueado;
    }


    ModelAndView siElUsuarioEstaLogueadoRealiza(Request request, Supplier<ModelAndView> bloque){

        if(!contextoDeUsuarioLogueado.esValidoElUsuarioLogueadoEn(request)){
            return new ModelAndView(null,"error404.hbs");
        }

        return bloque.get();
    }

    void cargarParametosHashMap() throws Exception {
        usuario = contextoDeUsuarioLogueado.getUsuarioLogueado();
        parametros.put("nombre", usuario.getNombre());
        parametros.put("apellido", usuario.getApellido());
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
