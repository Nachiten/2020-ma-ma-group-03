package domain.controllers;

import domain.entities.entidades.EntidadJuridica;
import domain.entities.usuarios.TipoUsuario;
import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static domain.entities.usuarios.TipoUsuario.ESTANDAR;

public class DarAltaUsuarioController {

    private Repositorio<Usuario> repoUsuario;
    private ContextoDeUsuarioLogueado contextoDeUsuarioLogueado;
    private Usuario usuario ;
    private Map<String, Object> parametros;
    private List<EntidadJuridica> entidadesJuridicas;

    public DarAltaUsuarioController(ContextoDeUsuarioLogueado contextoDeUsuarioLogueado) {
        this.contextoDeUsuarioLogueado = contextoDeUsuarioLogueado;
        this.parametros = new HashMap<>();
        this.usuario = new Usuario();
        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
        this.entidadesJuridicas = new ArrayList<>();
    }

    //Evalua si se accedio correctamente (previo inicio de sesion) y devuelve lo que corresponde
    //si se accedió a la página sin haberse logueado devuelve error 404
    private ModelAndView siElUsuarioEstaLogueadoRealiza(Request request, Supplier<ModelAndView> bloque){

        if(!contextoDeUsuarioLogueado.esValidoElUsuarioLogueadoEn(request)){
            return new ModelAndView(null,"error404.hbs");
        }

        return bloque.get();
    }

    private void cargarParametosHashMap() throws Exception {
        usuario = contextoDeUsuarioLogueado.getUsuarioLogueado();
        if (usuario == null){
            return;
        }
        parametros.put("nombre", usuario.getNombre());
        parametros.put("apellido", usuario.getApellido());
    }

    //devuelve la página correspondiente
    private ModelAndView modalAndViewAltaUsuario(){

        List<Usuario> usuarios = this.repoUsuario.buscarTodos();
        List<Usuario> usuariosNoHabilitados = usuarios.stream().filter(usuario -> !usuario.getEstoyHabilitado()).collect(Collectors.toList());
        parametros.put("usuariosNoHabilitados",usuariosNoHabilitados);
        Repositorio<EntidadJuridica> repoEntidadesJuridicas = FactoryRepositorio.get(EntidadJuridica.class);
        entidadesJuridicas = repoEntidadesJuridicas.buscarTodos();
        parametros.put("tiposUsuarios",TipoUsuario.values());
        parametros.put("entidadJuridica",entidadesJuridicas);
        return new ModelAndView(parametros,"altaUsuario.hbs");
        //buscar entidad jUridica parecido a moneda
    }

    public ModelAndView tiposDeUsuarios(Request request, Response response) throws Exception {
        cargarParametosHashMap();
        if (usuario == null){
            return new ModelAndView(null,"error404.hbs");
        }
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewAltaUsuario());
    }




    public ModelAndView darAltaUsuarioInhabilitado(Request request, Response response) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuario.buscar(idBuscado);


        usuarioBuscado.cambiarAHabilitado();

        this.repoUsuario.modificar(usuarioBuscado);

        parametros.put("mensaje", "El usuario se editó correctamente");
        return new ModelAndView(parametros, "modalInformativo2.hbs");

    }
    /*public ModelAndView listarUsuariosNoHabilitados(Request request, Response response) throws Exception {
        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewListarUsuariosNoHabilitados());
    }*/


    //-------------------------------------------------------------------------
    //                  PERSISTENCIA DE DATOS - POST
    //-------------------------------------------------------------------------

    //NO FUNCIONA LA PERSISTENCIA EN BD
    public ModelAndView guardarAltaDeUsuario(Request request, Response response) {

        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String nombreDeUsuario = request.queryParams("nombreDeUsuario");
        String contrasenia = request.queryParams("contrasenia");
        String tipoDeUsuario = request.queryParams("tipoUsuario");
        String entidadJuridica = request.queryParams("entidadJuridica");

        TipoUsuario tipoUsuario ;
        if(tipoDeUsuario.equals(TipoUsuario.ESTANDAR)) {
            tipoUsuario = TipoUsuario.ESTANDAR;
        }else{
            tipoUsuario = TipoUsuario.ADMIN;
        }

        Usuario usuarioApersistir = new Usuario(tipoUsuario,nombreDeUsuario,contrasenia,nombre,apellido);
        //usuarioApersistir.setEntidadJuridica(entidadJuridica);

        if(!validarPersistencia(repoUsuario,usuarioApersistir)){
            parametros.put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(parametros, "modalInformativo2.hbs");
        }

        parametros.put("mensaje","Se insertaron los datos correctamente");
        return new ModelAndView(parametros,"modalInformativo2.hbs");

    }

    private boolean validarPersistencia(Repositorio<?> objetoFactory, Object objetoClase){
        try {
            objetoFactory.agregar(objetoClase);
        }catch (Exception e){
            System.out.println("EXCEPCION: " + e.getMessage());
            return false;
        }
        return true;
    }
}
