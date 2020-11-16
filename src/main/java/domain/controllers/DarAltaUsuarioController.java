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
import java.util.stream.Collectors;


public class DarAltaUsuarioController {

    private Repositorio<Usuario> repoUsuario;
    private Map<String, Object> parametros;
    private OperadorController operadorController;
    private ModalAndViewController modalAndViewController;

    public DarAltaUsuarioController(ModalAndViewController modalAndViewController, OperadorController operadorController) {
        this.parametros = new HashMap<>();
        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
        this.operadorController = operadorController;
        this.modalAndViewController = modalAndViewController;
    }

    //devuelve la página correspondiente
    private ModelAndView modalAndViewAltaUsuario(){

        List<Usuario> usuarios = this.repoUsuario.buscarTodos();
        List<Usuario> usuariosNoHabilitados = usuarios.stream().filter(usuario -> !usuario.getEstoyHabilitado()).collect(Collectors.toList());
        parametros.put("usuariosNoHabilitados",usuariosNoHabilitados);
        Repositorio<EntidadJuridica> repoEntidadesJuridicas = FactoryRepositorio.get(EntidadJuridica.class);
       List<EntidadJuridica> entidadesJuridicas = repoEntidadesJuridicas.buscarTodos();
        parametros.put("tiposUsuarios",TipoUsuario.values());
        parametros.put("entidadJuridica",entidadesJuridicas);
        return new ModelAndView(parametros,"altaUsuario.hbs");
        //buscar entidad jUridica parecido a moneda
    }

    public ModelAndView tiposDeUsuarios(Request request, Response response) throws Exception {
        modalAndViewController.cargarParametrosHashMap();
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewAltaUsuario());
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


    public ModelAndView guardarAltaDeUsuario(Request request, Response response) {

        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String nombreDeUsuario = request.queryParams("nombreDeUsuario");
        String contrasenia = request.queryParams("contrasenia");
        String tipoDeUsuario = request.queryParams("tipoUsuario");
        String entidadJuridica = request.queryParams("entidadJuridica");

        TipoUsuario tipoUsuario ;
        if(tipoDeUsuario.equals(TipoUsuario.ESTANDAR.toString())) {
            tipoUsuario = TipoUsuario.ESTANDAR;
        }else{
            tipoUsuario = TipoUsuario.ADMIN;
        }

            Usuario usuarioApersistir = new Usuario(tipoUsuario,nombreDeUsuario,contrasenia,nombre,apellido);

        EntidadJuridica entidadJuridicaObtenida = obtenerEntidadJuridica(entidadJuridica);
         usuarioApersistir.setEntidadJuridica(entidadJuridicaObtenida);

        if(!operadorController.validarPersistencia(repoUsuario,usuarioApersistir)){
            parametros.put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(parametros, "modalInformativo2.hbs");
        }

        parametros.put("mensaje","Se insertaron los datos correctamente");
        return new ModelAndView(parametros,"modalInformativo2.hbs");

    }

    private EntidadJuridica obtenerEntidadJuridica(String entidadJuridica){
        String[] obtenerId = entidadJuridica.split("-");
        String id = obtenerId[0];
        int idEntidadJuridica = Integer.parseInt(id);
        Repositorio<EntidadJuridica> repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
        return repoEntidadJuridica.buscar(idEntidadJuridica);
    }

}
