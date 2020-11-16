package domain.controllers;

import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static javax.persistence.criteria.Predicate.BooleanOperator.AND;

public class ListarUsuariosController {

    private Repositorio<Usuario> repoUsuario;
    private Usuario usuario;
    private Map<String, Object> parametros;
    private ContextoDeUsuarioLogueado contextoDeUsuarioLogueado;


    public ListarUsuariosController(ContextoDeUsuarioLogueado contextoDeUsuarioLogueado) {

        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
        this.usuario = new Usuario();
        this.parametros = new HashMap<>();
        this.contextoDeUsuarioLogueado = contextoDeUsuarioLogueado;
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

    public ModelAndView modalAndViewListarUsuarios(){

        List<Usuario> usuarios = this.repoUsuario.buscarTodos();
        List<Usuario> usuariosHabilitados = usuarios.stream().filter(usuario -> usuario.getEstoyHabilitado() & !usuario.getNombreUsuario().equals(this.usuario.getNombreUsuario())).collect(Collectors.toList());
        parametros.put("listadoDeUsuarios", usuariosHabilitados);
        return new ModelAndView(parametros, "listadoDeUsuarios.hbs");
    }

    public ModelAndView listarUsuarios(Request request, Response response) throws Exception {

        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewListarUsuarios());
    }

    public ModelAndView eliminar(Request request, Response response) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuario.buscar(idBuscado);

        usuarioBuscado.cambiarAInhabilitado();

        this.repoUsuario.modificar(usuarioBuscado);

        parametros.put("mensaje","El usuario se eliminó correctamente");
        return new ModelAndView(parametros,"modalInformativo2.hbs") ;
    }
}
