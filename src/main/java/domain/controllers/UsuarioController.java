package domain.controllers;

import domain.entities.usuarios.TipoUsuario;
import domain.entities.usuarios.Usuario;
import domain.repositories.RepoUsuarios;
import org.apache.commons.codec.digest.DigestUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController {

    private RepoUsuarios repositorioDeUsuarios;
    private ContextoDeUsuarioLogueado contextoDeUsuarioLogueado;

    public UsuarioController(ContextoDeUsuarioLogueado contextoDeUsuarioLogueado) {
        repositorioDeUsuarios = new RepoUsuarios();
        this.contextoDeUsuarioLogueado = contextoDeUsuarioLogueado;
    }

    public ModelAndView loginUsuario(Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        List<Usuario> usuarios = this.repositorioDeUsuarios.getRepoUsuarios().buscarTodos();
        String nombreDeUsuario = request.queryParams("nombreDeUsuario");
        String contrasenia     = request.queryParams("contrasenia");
        String contraseniaEncriptada = DigestUtils.md5Hex(contrasenia);

        for ( Usuario unUsuario : usuarios) {
            if (unUsuario.getNombreUsuario().equals(nombreDeUsuario) &&  unUsuario.getContrasenia().equals(contraseniaEncriptada) && unUsuario.getTipo().toString().equals(TipoUsuario.ESTANDAR.toString())){
                contextoDeUsuarioLogueado.inicioSesionNuevoUsuarioLogueado(request, unUsuario);
                model.put("nombre", unUsuario.getNombreUsuario());
                model.put("apellido", unUsuario.getApellido());
                return new ModelAndView(model,"inicioEstandar.hbs");
            }
            if (unUsuario.getNombreUsuario().equals(nombreDeUsuario) &&  unUsuario.getContrasenia().equals(contraseniaEncriptada) && unUsuario.getTipo().toString().equals(TipoUsuario.ADMIN.toString())){
                contextoDeUsuarioLogueado.inicioSesionNuevoUsuarioLogueado(request, unUsuario);
                model.put("nombre", unUsuario.getNombreUsuario());
                model.put("apellido", unUsuario.getApellido());
                return new ModelAndView(model,"inicioAdministrador.hbs");
            }
        }
        model.put("mensaje", "El nombre de usuario o contraseña ingresados son incorrectos");
        return new ModelAndView(model, "modalInformativo.hbs");
    }

    public ModelAndView cerrarSesion(Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        model.put("mensaje", "¿Seguro que deseas cerrar sesión?");
        return new ModelAndView(model, "modalCerrarSesion.hbs");
    }

}
