package domain.controllers;

import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModalAndViewController {

    private Map<String, Object> parametros;
    private Usuario usuario;
    private Repositorio<Usuario> repoUsuarios;

    private ContextoDeUsuarioLogueado contextoDeUsuarioLogueado;

    public ModalAndViewController(ContextoDeUsuarioLogueado contextoDeUsuarioLogueado){
        this.parametros = new HashMap<>();
        this.usuario = new Usuario();
        this.repoUsuarios = FactoryRepositorio.get(Usuario.class);

        this.contextoDeUsuarioLogueado = contextoDeUsuarioLogueado;
    }

    void cargarParametrosHashMap(Request request) {
        usuario = contextoDeUsuarioLogueado.getUsuarioLogueado(request);
        parametros.put("nombre", usuario.getNombre());
        parametros.put("apellido", usuario.getApellido());
    }

    public ModelAndView siElUsuarioEstaLogueadoRealiza(Request request, Supplier<ModelAndView> bloque) {

        if(contextoDeUsuarioLogueado.esValidoElUsuarioLogueadoEn(request)){
            this.cargarParametrosHashMap(request);
            return bloque.get();
        }
        return new ModelAndView(null,"error404.hbs");
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public Usuario getUsuario(){
        return repoUsuarios.buscar(usuario.getId());
    }
}
