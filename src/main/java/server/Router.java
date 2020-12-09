package server;

import domain.controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        // Controllers
        ContextoDeUsuarioLogueado contextoDeUsuarioLogueado = new ContextoDeUsuarioLogueado();
        OperadorController operadorController = new OperadorController();
        ModalAndViewController modalAndViewController = new ModalAndViewController(contextoDeUsuarioLogueado);

        UsuarioController usuarioController = new UsuarioController(contextoDeUsuarioLogueado);
        InicioController inicioController 	= new InicioController(contextoDeUsuarioLogueado);
        MainController mainController = new MainController(modalAndViewController);

        IngresosController ingresosController = new IngresosController(modalAndViewController, operadorController);
        PresupuestosController presupuestosController = new PresupuestosController(modalAndViewController, operadorController);
        EgresosController egresosController = new EgresosController(modalAndViewController, operadorController);
        CriteriosController criteriosController = new CriteriosController(modalAndViewController, operadorController);
        AsociacionOperacionesController asociacionOperacionesController = new AsociacionOperacionesController(modalAndViewController);
        MensajesController mensajesController = new MensajesController(modalAndViewController);
        PerfilUsuarioEstandarController perfilUsuarioEstandarController = new PerfilUsuarioEstandarController(modalAndViewController);

        AccionesEnUsuariosController accionesEnUsuariosController = new AccionesEnUsuariosController(modalAndViewController, operadorController);

        AccionesEnProveedoresController accionesEnProveedoresController = new AccionesEnProveedoresController(modalAndViewController, operadorController);
        AccionesEntidadJuridicaController accionesEntidadJuridicaController = new AccionesEntidadJuridicaController(modalAndViewController, operadorController);

        ValidadorTransparenciaController validadorTransparenciaController = new ValidadorTransparenciaController(modalAndViewController);

        // Pagina iniciar sesión
        Spark.get("/", inicioController::inicio, Router.engine);

        // Cerrar sesion
        Spark.post("/", usuarioController::cerrarSesion, Router.engine);

        // Se verifica que el usuario exista
        Spark.post("/inicio", usuarioController::loginUsuario, Router.engine);

        // Paginas una vez logueado GET para usuarios ESTANDAR
        Spark.get("/ingresos", ingresosController::ingresos, Router.engine);
        Spark.get("/egresos", egresosController::egresos, Router.engine);
        Spark.get("/presupuestos", presupuestosController::presupuestos, Router.engine);
        Spark.get("/listadoOperaciones", asociacionOperacionesController::listadoOperaciones, Router.engine);
        Spark.get("/asociarOperacion", asociacionOperacionesController::asociarOperacion, Router.engine);
        Spark.get("/mensajes", mensajesController::mensajes, Router.engine);
        Spark.post("/mensajes/:id",mensajesController::mostrarContenidoMensaje,Router.engine);

        //Perfil de usuario estandar
        Spark.get("/editarPerfil",perfilUsuarioEstandarController::mostrarPaginaPerfilUsuarioEstandar,Router.engine);
        Spark.post("/actualizarDatosPerfil",perfilUsuarioEstandarController::actualizarDatosPerfilUsuarioEstandar,Router.engine);
        Spark.post("/actualizarContrasenia",perfilUsuarioEstandarController::actualizarContraseniaPerfilUsuarioEstandar,Router.engine);
        Spark.post("/editarPerfil/detalleEntidadJuridica", perfilUsuarioEstandarController::verEntidadJuridicaAsociada,Router.engine);

        //muestra página inicio dependiendo del tipo de usuario logueado
        Spark.get("/inicio", mainController::principal, Router.engine);

        // Guardar los datos de las ventanas POST
        Spark.post("/egresos", egresosController::guardarOperacionDeEgreso, Router.engine);
        Spark.post("/egresos/proveedor", egresosController::verDetalleProveedor, Router.engine);
        Spark.post("/egresos/detalle/:id", egresosController::verDetalleEgreso, Router.engine);
        Spark.post("/egresos/nuevasCategorias/:id", egresosController::asociarNuevasCategorias, Router.engine);
        Spark.post("/egresos/categoriasNuevas/:id", egresosController::actualizarCategorias, Router.engine);

        Spark.post("/egresos/items/:id", egresosController::verItemsEgreso, Router.engine);
        Spark.post("/egresos/categorias/:id", egresosController::verCategoriasEgreso, Router.engine);
        Spark.post("/egresos/revisores/:id", egresosController::verRevisores, Router.engine);
        Spark.post("/egresos/nombreCategoria", egresosController::verEgresosPorCategoria, Router.engine);
        Spark.post("/egresos/presupuestos/:id", egresosController::verPresupuestosAsociados, Router.engine);

        Spark.post("/ingresos", ingresosController::guardarOperacionDeIngreso, Router.engine);
        Spark.post("/ingresos/detalle/:id", ingresosController::verDetalleIngreso, Router.engine);
        Spark.post("/ingresos/egresos/:id", ingresosController::verOperacionesVinculadas, Router.engine);

        Spark.post("/presupuestos", presupuestosController::guardarPresupuesto, Router.engine);
        Spark.post("/presupuestos/proveedor/:id", presupuestosController::verDetalleProveedor, Router.engine);
        Spark.post("/presupuestos/categorias/:id", presupuestosController::verCategoriasPresupuesto, Router.engine);
        Spark.post("/presupuestos/items/:id", presupuestosController::verItemsPresupuesto, Router.engine);
        Spark.post("/presupuestos/egreso", presupuestosController::verEgresoElegido, Router.engine);
        Spark.post("/presupuestos/operacionDeEgreso", presupuestosController::asociarEgreso, Router.engine);

        Spark.post("/asociarOperacion", asociacionOperacionesController::ejecutarVinculacion, Router.engine);

        Spark.post("/validadorDeTransparencia", validadorTransparenciaController::programarValidadorDeTransparencia, Router.engine);
        Spark.post("/validadorDeTransparenciaAhora", validadorTransparenciaController::ejecutarValidadorDeTransparenciaAhora, Router.engine);

        //Paginas una vez logueado GET para usuario ADMIN
        Spark.get("/accionesUsuarios", accionesEnUsuariosController::mostrarPaginaAccionesUsuarios, Router.engine);
        Spark.get("/accionesProveedores", accionesEnProveedoresController::mostrarPaginaAccionesProveedores, Router.engine);
        Spark.get("/accionesEntidadesJuridicas", accionesEntidadJuridicaController::mostrarPaginaAccionesEntidadJuridica,Router.engine);
        Spark.get("/accionesCriterios", criteriosController::mostrarPaginaCriterios, Router.engine);

        //Acciones usuarios
        Spark.get("/nuevoUsuario", accionesEnUsuariosController::mostrarModalNuevoUsuario, Router.engine);
        Spark.post("/confirmarNuevoUsuario", accionesEnUsuariosController::mostrarModalParaConfirmarNuevoUsuario, Router.engine);
        Spark.post("/guardarUsuario", accionesEnUsuariosController::guardarNuevoUsuario, Router.engine);

        Spark.get("/habilitarUsuario", accionesEnUsuariosController::mostrarModalHabilitarUsuario, Router.engine);
        Spark.post("/confirmarHabilitarUsuario/:id", accionesEnUsuariosController::mostrarModalParaConfirmarHabilitarUsuario, Router.engine);
        Spark.post("/habilitarUsuario/:id", accionesEnUsuariosController::habilitarUsuario, Router.engine);

        Spark.get("/editarUsuario", accionesEnUsuariosController::mostrarModalEditarUsuarios, Router.engine);
        Spark.post("/editarUsuario/confirmarEditarUsuario/:id", accionesEnUsuariosController::mostrarModalParaConfirmarEditarUnUsuario, Router.engine);

        Spark.get("/editarUsuario/modificar/:id", accionesEnUsuariosController::mostrarModalParaEditarUnUsuario, Router.engine);
        Spark.post("/editarUsuario/modificar/confirmarModificar/:id", accionesEnUsuariosController::mostrarModalparaConfirmarCambiosRealizadosEnUnUsuario, Router.engine);
        Spark.post("/editarUsuario/modificar/guardar/:id", accionesEnUsuariosController::guardarCambiosDeEdicionDelUsuario, Router.engine);

        Spark.post("/editarUsuario/confirmarBajaUsuario/:id", accionesEnUsuariosController::mostrarModalParaConfirmarBajaDeUsuario, Router.engine);
        Spark.delete("/editarUsuario/darDeBaja/:id", accionesEnUsuariosController::darDeBajaUsuario, Router.engine);

        //Acciones proveedor
        Spark.get("/nuevoProveedor", accionesEnProveedoresController::mostrarModalNuevoProveedor, Router.engine);
        Spark.post("/confirmarNuevoProveedor", accionesEnProveedoresController::mostrarModalParaConfirmarNuevoProveedor, Router.engine);
        Spark.post("/guardarNuevoProveedor", accionesEnProveedoresController::guardarNuevoProveedor, Router.engine);

        Spark.get("/habilitarProveedor", accionesEnProveedoresController::mostrarModalHabilitarProveedor, Router.engine);
        Spark.post("/habilitarProveedor/confirmarHabilitar/:id", accionesEnProveedoresController::mostrarModalParaConfirmarHabilitarProveedor, Router.engine);
        Spark.post("/habilitarProveedor/:id", accionesEnProveedoresController::mostrarConfirmacionHabilitarProveedor, Router.engine);

        Spark.get("/editarProveedor", accionesEnProveedoresController::mostrarModalEditarProveedores, Router.engine);
        Spark.post("/editarProveedor/confirmarEditarProveedor/:id", accionesEnProveedoresController::mostrarModalParaConfirmarEditarUnProveedor, Router.engine);
        Spark.get("/editarProveedor/modificar/:id", accionesEnProveedoresController::mostrarModalParaEditarUnProveedor, Router.engine);
        Spark.post("/editarProveedor/modificar/confirmarCambiosDatosProveedor/:id", accionesEnProveedoresController::mostrarModalParaConfirmarCambiosEnDatosDelProveedor, Router.engine);
        Spark.post("/editarProveedor/modificar/confirmacionCambiosDatosProveedor/:id", accionesEnProveedoresController::mostrarModalConfirmacionDatosActualizadosDelProveedor, Router.engine);

        Spark.post("/editarProveedor/modificar/confirmarEditarDireccionProveedor/:id", accionesEnProveedoresController::mostrarModalParaConfirmarEditarDireccionProveedor, Router.engine);
        Spark.get("/editarProveedor/modificar/actualizarDireccion/:id", accionesEnProveedoresController::mostrarModalParaActualizarDireccionProveedor, Router.engine);
        Spark.post("/editarProveedor/modificar/confirmarActualizarDireccion/:id", accionesEnProveedoresController::mostrarModalParaConfirmarActualizarDireccionProveedor, Router.engine);
        Spark.post("/editarProveedor/modificar/actualizacionDireccion/:id", accionesEnProveedoresController::mostrarConfirmacionDatosActualizadosDireccionProveedor, Router.engine);

        Spark.post("/editarProveedor/confirmarBajaProveedor/:id", accionesEnProveedoresController::mostrarModalParaConfirmarBajaDeUnProveedor, Router.engine);
        Spark.delete("/editarproveedor/darDeBaja/:id", accionesEnProveedoresController::mostrarModalConfirmacionBajaProveedor, Router.engine);

        //Acciones entidad jurídica
        Spark.get("/nuevaEntidadJuridica", accionesEntidadJuridicaController::mostrarModalNuevaEntidadJuridica, Router.engine);
        Spark.post("/confirmarNuevaEntidadJuridica", accionesEntidadJuridicaController::mostrarModalParaConfirmarNuevaEntidadJuridica, Router.engine);
        Spark.post("/GuardarNuevaEntidadJuridica", accionesEntidadJuridicaController::mostrarModalConfirmacionNuevaEntidadJuridica,Router.engine);
        Spark.get("/habilitarEntidadesJuridicas", accionesEntidadJuridicaController::mostrarModalHabilitarEntidadesjuridicas, Router.engine);
        Spark.get("/editarEntidadesJuridicas", accionesEntidadJuridicaController::mostrarModalEditarEntidadesJuridicas, Router.engine);

        //Acciones criterios
        Spark.post("/accionesCriterios", criteriosController::guardarCriterio, Router.engine);
        Spark.get("/validadorDeTransparencia", validadorTransparenciaController::validadorTransparencia,Router.engine);


        Spark.post("/guardarDocumentoEgreso", egresosController::guardarDocumentoEgreso ,Router.engine);
        Spark.post("/descargarDocumentoEgreso", egresosController::descargarDocumentoEgreso, Router.engine);
        Spark.get("/descargarDocumento", egresosController::descargarDocumento);

        //
        Spark.get("/*", inicioController::retornarError, Router.engine);
    }
}
