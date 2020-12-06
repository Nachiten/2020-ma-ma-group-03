package domain.controllers;

import domain.entities.entidades.EntidadJuridica;
import domain.entities.usuarios.TipoUsuario;
import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import validadoresContrasenia.*;

import java.util.List;
import java.util.stream.Collectors;

public class AccionesEnUsuariosController {

    private ModalAndViewController modalAndViewController;
    private OperadorController operadorController;
    private Repositorio<Usuario> repoUsuario;

    public AccionesEnUsuariosController(ModalAndViewController modalAndViewController, OperadorController operadorController) {
        this.modalAndViewController = modalAndViewController;
        this.operadorController = operadorController;
        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
    }

    private ModelAndView modalAndViewAccionesUsuario() {
        return new ModelAndView(modalAndViewController.getParametros(), "accionesUsuarios.hbs");
    }

    //Primero muestra la página de las acciones sobre los usuarios
    public ModelAndView mostrarPaginaAccionesUsuarios(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewAccionesUsuario);
    }

    private ModelAndView modalAndViewNuevoUsuario() {
        Repositorio<EntidadJuridica> repoEntidadesJuridicas = FactoryRepositorio.get(EntidadJuridica.class);
        List<EntidadJuridica> entidadesJuridicas = repoEntidadesJuridicas.buscarTodos();
        modalAndViewController.getParametros().put("tiposUsuarios", TipoUsuario.values());
        modalAndViewController.getParametros().put("entidadesJuridicas",entidadesJuridicas);
        return new ModelAndView(modalAndViewController.getParametros(), "modalNuevoUsuario.hbs");
    }

    //Se muestra el formulario para ingresar un nuevo usuario
    public ModelAndView mostrarModalNuevoUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewNuevoUsuario);
    }

    private ModelAndView modalAndViewConfirmarNuevoUsuario() {
        modalAndViewController.getParametros().put("mensaje", "¿Está seguro que quiere crear a este nuevo usuario?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarNuevoUsuario.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarNuevoUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewConfirmarNuevoUsuario);
    }

    private EntidadJuridica obtenerEntidadJuridica(String entidadJuridica){
        String[] obtenerId = entidadJuridica.split("-");
        String id = obtenerId[0];
        int idEntidadJuridica = Integer.parseInt(id);
        Repositorio<EntidadJuridica> repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
        return repoEntidadJuridica.buscar(idEntidadJuridica);
    }

    private TipoUsuario soyDelTipoUsuario(String tipoDeUsuario){
        if(tipoDeUsuario.equals(TipoUsuario.ESTANDAR.toString())) {
            return TipoUsuario.ESTANDAR;
        }
            return TipoUsuario.ADMIN;
    }

    private ModelAndView modalAndViewGuardarNuevoUsuario(Request request) {
        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String nombreDeUsuario = request.queryParams("nombreDeUsuario");
        String contrasenia = request.queryParams("contrasenia");
        String tipoDeUsuario = request.queryParams("tipoUsuario");
        String entidadJuridica = request.queryParams("entidadJuridica");

        if (operadorController.soyCadenaVaciaONula(nombre)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un nombre");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(apellido)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un apellido");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(nombreDeUsuario)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un nombre de usuario");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(contrasenia)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese una contraseña");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.noSeleccioneUnaOpcion(entidadJuridica)){
            modalAndViewController.getParametros().put("mensaje", "Selecciona una entidad jurídica");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.noSeleccioneUnaOpcion(tipoDeUsuario)){
            modalAndViewController.getParametros().put("mensaje", "Selecciona un tipo de usuario");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        TipoUsuario tipoUsuario = soyDelTipoUsuario(tipoDeUsuario);

        Usuario usuarioApersistir = new Usuario(tipoUsuario,nombreDeUsuario,contrasenia,nombre,apellido);

        EntidadJuridica entidadJuridicaObtenida = obtenerEntidadJuridica(entidadJuridica);
        usuarioApersistir.setEntidadJuridica(entidadJuridicaObtenida);

        ValidadorCredenciales miValidador = new ValidadorCredenciales(new ValidadorEspacios(), new ValidadorLongitud(8), new ValidadorMayusculas(1), new ValidadorNumeros(1));

        if (!miValidador.esSegura(contrasenia)){
            modalAndViewController.getParametros().put("mensaje", "La contraseña no es segura. Debe contener 1 mayuscula, 1 numero y ser de 8 caracteres. Sin espacios");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if (yaExisteNombreUsuario(nombreDeUsuario)){
            modalAndViewController.getParametros().put("mensaje", "Ya existe un usuario con este nombre de usuario.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if(operadorController.persistenciaNoValida(repoUsuario,usuarioApersistir)){
            modalAndViewController.getParametros().put("mensaje", "No se pudo crear el nuevo usuario, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se creo exitosamente el nuevo usuario.");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativoConfirmacionDeNuevoUsuario.hbs");
    }

    //Se guarda en la BD el usuario ingresado en el formulario
    public ModelAndView guardarNuevoUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewGuardarNuevoUsuario(request));
    }

    private boolean yaExisteNombreUsuario(String nombreUsuario){
        List<Usuario> usuarios = repoUsuario.buscarTodos();

        return usuarios.stream().anyMatch(unUsuario -> unUsuario.getNombreUsuario().equals(nombreUsuario));
    }

    private ModelAndView modalAndViewHabilitarUsuario() {
        List<Usuario> usuarios = this.repoUsuario.buscarTodos();
        List<Usuario> usuariosInhabilitados = usuarios.stream().filter(usuario -> !usuario.getEstoyHabilitado()).collect(Collectors.toList());
        modalAndViewController.getParametros().put("usuariosInhabilitados",usuariosInhabilitados);
        return new ModelAndView(modalAndViewController.getParametros(),"modalHabilitarUsuarios.hbs");
    }

    public ModelAndView mostrarModalHabilitarUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewHabilitarUsuario);
    }

    private ModelAndView modalAndViewConfirmarHabilitarUsuario(Request request) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuario.buscar(idBuscado);
        modalAndViewController.getParametros().put("id", usuarioBuscado.getId());
        modalAndViewController.getParametros().put("mensaje", "¿Está seguro que quiere habilitar al usuario "+usuarioBuscado.getNombreUsuario()+"?");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativoConfirmarHabilitarUsuario.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarHabilitarUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewConfirmarHabilitarUsuario(request));
    }

    private ModelAndView modalAndViewHabilitarUsuario(Request request){
        int idBuscado = Integer.parseInt(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuario.buscar(idBuscado);
        usuarioBuscado.cambiarAHabilitado();
        this.repoUsuario.modificar(usuarioBuscado);
        modalAndViewController.getParametros().put("mensaje", "El usuario "+usuarioBuscado.getNombreUsuario()+" se habilitó correctamente");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }

    public ModelAndView habilitarUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewHabilitarUsuario(request));
    }

    private ModelAndView modalAndViewEditarUsuarios() {
        List<Usuario> usuarios = this.repoUsuario.buscarTodos();
        List<Usuario> usuariosHabilitados = usuarios.stream().filter(usuario -> usuario.getEstoyHabilitado() & !usuario.getNombreUsuario().equals(modalAndViewController.getUsuario().getNombreUsuario())).collect(Collectors.toList());
        modalAndViewController.getParametros().put("listadoDeUsuarios", usuariosHabilitados);
        return new ModelAndView(modalAndViewController.getParametros(), "modalEditarUsuarios.hbs");
    }

    public ModelAndView mostrarModalEditarUsuarios(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewEditarUsuarios);
    }

    private ModelAndView modalAndViewConfirmarBajaUsuario(Request request) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuario.buscar(idBuscado);
        modalAndViewController.getParametros().put("id", usuarioBuscado.getId());
        modalAndViewController.getParametros().put("mensaje","¿Está seguro que quiere dar de baja al usuario "+usuarioBuscado.getNombreUsuario()+"?");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativoConfirmarBajaUsuario.hbs") ;
    }

    public ModelAndView mostrarModalParaConfirmarBajaDeUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewConfirmarBajaUsuario(request));
    }

    private ModelAndView modalAndViewDarDeBajaUsuario(Request request){
        int idBuscado = Integer.parseInt(request.params("id"));
        Usuario usuarioBuscado = this.repoUsuario.buscar(idBuscado);
        usuarioBuscado.cambiarAInhabilitado();
        this.repoUsuario.modificar(usuarioBuscado);
        modalAndViewController.getParametros().put("mensaje","Se dio de baja al usuario "+usuarioBuscado.getNombreUsuario()+" correctamente");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs") ;
    }

    public ModelAndView darDeBajaUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewDarDeBajaUsuario(request));
    }

    private ModelAndView modalAndViewConfirmarEditarUnUsuario(Request request) {
        int idUsuario = Integer.parseInt(request.params("id"));
        Usuario usuarioAEditar = this.repoUsuario.buscar(idUsuario);
        modalAndViewController.getParametros().put("miId", usuarioAEditar.getId());
        modalAndViewController.getParametros().put("mensaje","¿Está seguro que quiere editar al usuario "+usuarioAEditar.getNombreUsuario()+"?");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativoConfirmarEditarUnUsuario.hbs") ;
    }

    public ModelAndView mostrarModalParaConfirmarEditarUnUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewConfirmarEditarUnUsuario(request));
    }

    private ModelAndView modalAndViewParaEditarUnUsuario(Request request){
        int idUsuario = Integer.parseInt(request.params("id"));
        Usuario usuarioAEditar = this.repoUsuario.buscar(idUsuario);
        modalAndViewController.getParametros().put("miId", usuarioAEditar.getId());
        modalAndViewController.getParametros().put("miNombre", usuarioAEditar.getNombre());
        modalAndViewController.getParametros().put("miApellido", usuarioAEditar.getApellido());
        modalAndViewController.getParametros().put("miNombreDeUsuario", usuarioAEditar.getNombreUsuario());
        modalAndViewController.getParametros().put("miContrasenia", usuarioAEditar.getContrasenia());

        Repositorio<EntidadJuridica> repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
        EntidadJuridica miEntidadJuridica = repoEntidadJuridica.buscar(usuarioAEditar.getEntidadJuridica().getId());
        modalAndViewController.getParametros().put("idEntidadJuridica", miEntidadJuridica.getId());
        modalAndViewController.getParametros().put("razonSocialEntidadJuridica", miEntidadJuridica.getRazonSocialEntidadJuridica());
        modalAndViewController.getParametros().put("nombreEntidadJuridica", miEntidadJuridica.getNombreEntidadJuridica());


        // modalAndViewController.getParametros().put("soyRevisor", usuarioAEditar.getSoyRevisor());

        Repositorio<EntidadJuridica> repoEntidadesJuridicas = FactoryRepositorio.get(EntidadJuridica.class);
        List<EntidadJuridica> entidadesJuridicas = repoEntidadesJuridicas.buscarTodos();
        modalAndViewController.getParametros().put("entidadesJuridicas",entidadesJuridicas);

        return new ModelAndView(modalAndViewController.getParametros(),"modalParaEditarUnUsuario.hbs") ;
    }

    public ModelAndView mostrarModalParaEditarUnUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewParaEditarUnUsuario(request));
    }

    private ModelAndView modalAndViewParaConfirmarCambiosEnUnUsuario(Request request) {
        int idUsuario = Integer.parseInt(request.params("id"));
        Usuario usuarioAEditar = this.repoUsuario.buscar(idUsuario);
        modalAndViewController.getParametros().put("miId", usuarioAEditar.getId());
        modalAndViewController.getParametros().put("mensaje","¿Está seguro que quiere guardar los cambios realizados del usuario "+usuarioAEditar.getNombreUsuario()+"?");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativoConfirmarCambiosEnUnUsuario.hbs") ;
    }

    public ModelAndView mostrarModalparaConfirmarCambiosRealizadosEnUnUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewParaConfirmarCambiosEnUnUsuario(request));
    }

    private ModelAndView modalAndViewParaGuardarCambiosDeEdicionDelUsuario(Request request){
        int idUsuario = Integer.parseInt(request.params("id"));
        Usuario usuarioEditadoAGuardar = this.repoUsuario.buscar(idUsuario);
        String nombreEditado = request.queryParams("nombre");
        String apellidoEditado = request.queryParams("apellido");
        String nombreDeUsuarioEditado = request.queryParams("nombreDeUsuario");
        String contraseniaEditado = request.queryParams("contrasenia");
        String entidadJuridicaQueConservo = request.queryParams("miEntidadJuridica");
        String entidadJuridicaEditado = request.queryParams("entidadJuridica");

        //verifico si hubo cambio de entidad jurídica
        if (operadorController.noSeleccioneUnaOpcion(entidadJuridicaEditado)){
            usuarioEditadoAGuardar.guardarCambiosEfectuadosEnMisAtributos(nombreEditado, apellidoEditado, nombreDeUsuarioEditado, contraseniaEditado);

        }else{
            Repositorio<EntidadJuridica> repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
            int idEntidadjuridica = Integer.parseInt(entidadJuridicaEditado);
            EntidadJuridica nuevaEntidadJuridica = repoEntidadJuridica.buscar(idEntidadjuridica);
            usuarioEditadoAGuardar.guardarCambiosEfectuadosEnMisAtributos(nombreEditado, apellidoEditado, nombreDeUsuarioEditado, contraseniaEditado, nuevaEntidadJuridica);
        }

        repoUsuario.modificar(usuarioEditadoAGuardar);

        modalAndViewController.getParametros().put("mensaje","Los datos del usuario "+usuarioEditadoAGuardar.getNombreUsuario()+" se actualizaron correctamente");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs") ;
    }

    public ModelAndView guardarCambiosDeEdicionDelUsuario(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewParaGuardarCambiosDeEdicionDelUsuario(request));
    }

}
