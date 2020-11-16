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
        EgresosController egresosController = new EgresosController(modalAndViewController, operadorController);
        PresupuestosController presupuestosController = new PresupuestosController(modalAndViewController, operadorController);
        MensajesController mensajesController = new MensajesController(modalAndViewController);
        AsociacionOperacionesController asociacionOperacionesController = new AsociacionOperacionesController(modalAndViewController);

        DarAltaUsuarioController darAltaUsuarioController = new DarAltaUsuarioController(modalAndViewController, operadorController);
        BajaUsuarioController bajaUsuarioController = new BajaUsuarioController(modalAndViewController);
        AltaEntidadJuridicaController altaEntidadJuridicaController = new AltaEntidadJuridicaController(contextoDeUsuarioLogueado);
        BajaProveedorController bajaProveedorController = new BajaProveedorController(contextoDeUsuarioLogueado);
        CriteriosController criteriosController = new CriteriosController(modalAndViewController, operadorController);

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
        Spark.get("/criterios", criteriosController::criterios, Router.engine);
        Spark.get("/listadoOperaciones", asociacionOperacionesController::listadoOperaciones, Router.engine);
        Spark.get("/asociarOperacion", asociacionOperacionesController::asociarOperacion, Router.engine);
        Spark.get("/mensajes", mensajesController::mensajes, Router.engine);
        Spark.get("/mensajes/:id",mensajesController::mostrarContenidoMensaje,Router.engine);
        Spark.get("/inicio", mainController::principal, Router.engine);

        // Guardar los datos de las ventanas POST
        Spark.post("/egresos", egresosController::guardarOperacionDeEgreso, Router.engine);
        Spark.post("/ingresos", ingresosController::guardarOperacionDeIngreso, Router.engine);
        Spark.post("/presupuestos", presupuestosController::guardarPresupuesto, Router.engine);
        Spark.post("/criterios", criteriosController::guardarCriterio, Router.engine);
        Spark.post("/asociarOperacion", asociacionOperacionesController::ejecutarVinculacion, Router.engine);

        //Paginas una vez logueado GET para usuario ADMIN
        Spark.get("/altaUsuario",darAltaUsuarioController::tiposDeUsuarios, Router.engine);
        Spark.get("/bajaUsuario", bajaUsuarioController::listarUsuarios,Router.engine);

        // Delete para eliminar un usuario
        Spark.delete("/bajaUsuario/eliminar/:id", bajaUsuarioController::eliminar,Router.engine);
        Spark.post("/altaUsuario/alta/:id", darAltaUsuarioController::darAltaUsuarioInhabilitado,Router.engine);

        //Guardar los datos de las pestañas POST de un usuario
        Spark.post("/altaUsuario",darAltaUsuarioController::guardarAltaDeUsuario,Router.engine);

        // Proveedor
        Spark.get("/bajaProveedor",bajaProveedorController::listarProveedores , Router.engine);

        //Delete para eliminar un proveedor
        Spark.delete("/bajaProveedor/eliminar/:id",bajaProveedorController::eliminar,Router.engine);

        //Entidad Juridica
        Spark.get("/altaEntidadJuridica",altaEntidadJuridicaController::mostrarPaginaAltaEntidadJuridica,Router.engine);

        //Spark.get("/*", (request, response) -> "Error 404 no hay nada aca.");
        Spark.get("/*", inicioController::retornarError, Router.engine);
    }
}
