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

    private void cargarParametrosHashMap() throws Exception {
        usuario = contextoDeUsuarioLogueado.getUsuarioLogueado();
        parametros.put("nombre", usuario.getNombre());
        parametros.put("apellido", usuario.getApellido());
    }

    public ModelAndView siElUsuarioEstaLogueadoRealiza(Request request, Supplier<ModelAndView> bloque) throws Exception {

        if(contextoDeUsuarioLogueado.esValidoElUsuarioLogueadoEn(request)){
            this.cargarParametrosHashMap();
            return bloque.get();
        }
        return new ModelAndView(null,"error404.hbs");
    }



    public Map<String, Object> getParametros() {
        return parametros;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
