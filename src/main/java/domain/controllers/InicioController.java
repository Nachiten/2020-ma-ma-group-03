package domain.controllers;

import domain.entities.usuarios.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicioController {

    private UsuarioController unUsuarioController;
    private AdministradorDeSesion administradorDeSesion;
    private List<Usuario> usuarios;

    public InicioController(){
        unUsuarioController = new UsuarioController();
        administradorDeSesion = new AdministradorDeSesion();
        usuarios = new ArrayList<>();
    }

    //Devuelve la pantalla inicial (login) y cierra la sesión que se encuentra abierta
    public ModelAndView inicio(Request request, Response response) {
        administradorDeSesion.cerrarSesion(request);
        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView loginUsuario(Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        List<Usuario> usuarios = this.unUsuarioController.getRepoUsuarios().buscarTodos();
        String nombreDeUsuario = request.queryParams("nombreDeUsuario");
        String contrasenia     = request.queryParams("contrasenia");
        String contraseniaEncriptada = DigestUtils.md5Hex(contrasenia);

        for ( Usuario unUsuario : usuarios) {
            if (unUsuario.getNombreUsuario().equals(nombreDeUsuario) &&  unUsuario.getContrasenia().equals(contraseniaEncriptada)){
                model.put("tipoUsuario", unUsuario.getTipo());
                model.put("usuario", unUsuario);
                model.put("nombre", unUsuario.getNombreUsuario());
                model.put("apellido", unUsuario.getApellido());
                model.put("id", unUsuario.getId());
                administradorDeSesion.iniciarSesion(request, unUsuario);
                return new ModelAndView(model,"inicio.hbs");
            }
        }
        model.put("mensaje", "El nombre de usuario o contraseña ingresados son incorrectos");
        return new ModelAndView(model, "modalInformativo.hbs");
    }

    public ModelAndView mensajes(Request request, Response response) {
        return new ModelAndView(null, "mensajes.hbs");
    }

    public ModelAndView error404(Request request, Response response){
        return new ModelAndView(null, "error404.hbs");
    }

    public int obtenerIdDeUsuarioLogueado(){
        return administradorDeSesion.getUsuario().getId();
    }

    public Usuario obtenerUsuarioLogueado(){
        return administradorDeSesion.getUsuario();
    }
}
