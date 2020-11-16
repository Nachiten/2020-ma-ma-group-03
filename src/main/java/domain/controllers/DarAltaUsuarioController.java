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
    private OperadorController operadorController;
    private ModalAndViewController modalAndViewController;

    public DarAltaUsuarioController(ModalAndViewController modalAndViewController,OperadorController operadorController) {
        this.modalAndViewController = modalAndViewController;
        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
        this.operadorController = operadorController;

    }


    //devuelve la página correspondiente
    private ModelAndView modalAndViewAltaUsuario(){

        List<Usuario> usuarios = this.repoUsuario.buscarTodos();
        List<Usuario> usuariosNoHabilitados = usuarios.stream().filter(usuario -> !usuario.getEstoyHabilitado()).collect(Collectors.toList());
        modalAndViewController.getParametros().put("usuariosNoHabilitados",usuariosNoHabilitados);
        Repositorio<EntidadJuridica> repoEntidadesJuridicas = FactoryRepositorio.get(EntidadJuridica.class);
        List<EntidadJuridica> entidadesJuridicas = repoEntidadesJuridicas.buscarTodos();
        modalAndViewController.getParametros().put("tiposUsuarios",TipoUsuario.values());
        modalAndViewController.getParametros().put("entidadJuridica",entidadesJuridicas);
        return new ModelAndView(modalAndViewController.getParametros(),"altaUsuario.hbs");
        //buscar entidad jUridica parecido a moneda
    }

    public ModelAndView tiposDeUsuarios(Request request, Response response) throws Exception {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewAltaUsuario);
    }

    public ModelAndView darAltaUsuarioInhabilitado(Request request, Response response) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuario.buscar(idBuscado);


        usuarioBuscado.cambiarAHabilitado();

        this.repoUsuario.modificar(usuarioBuscado);

        modalAndViewController.getParametros().put("mensaje", "El usuario se habilitó correctamente");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");

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
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje","Se insertaron los datos correctamente");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");

    }

    private EntidadJuridica obtenerEntidadJuridica(String entidadJuridica){
        String[] obtenerId = entidadJuridica.split("-");
        String id = obtenerId[0];
        int idEntidadJuridica = Integer.parseInt(id);
        Repositorio<EntidadJuridica> repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
        return repoEntidadJuridica.buscar(idEntidadJuridica);
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
