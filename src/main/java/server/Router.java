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
        ModalAndViewController modalAndViewController = new ModalAndViewController(contextoDeUsuarioLogueado);
        UsuarioController usuarioController = new UsuarioController(contextoDeUsuarioLogueado);
        InicioController inicioController 	= new InicioController(contextoDeUsuarioLogueado);
        mainController mainController = new mainController(modalAndViewController);
        IngresosController ingresosController = new IngresosController(modalAndViewController);
        PresupuestosController presupuestosController = new PresupuestosController(modalAndViewController);
        EgresosController egresosController = new EgresosController(modalAndViewController);
        CriteriosController criteriosController = new CriteriosController(modalAndViewController);
        AsociacionOperacionesController asociacionOperacionesController = new AsociacionOperacionesController(modalAndViewController);
        DarAltaUsuarioController darAltaUsuarioController = new DarAltaUsuarioController(contextoDeUsuarioLogueado);
        ListarUsuariosController listarUsuariosController = new ListarUsuariosController(contextoDeUsuarioLogueado);

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
        Spark.get("/mensajes", mainController::mensajes, Router.engine);
        Spark.get("/inicio", mainController::principal, Router.engine);

        // Guardar los datos de las ventanas POST
        Spark.post("/egresos", egresosController::guardarOperacionDeEgreso, Router.engine);
        Spark.post("/ingresos", ingresosController::guardarOperacionDeIngreso, Router.engine);
        Spark.post("/presupuestos", presupuestosController::guardarPresupuesto, Router.engine);
        Spark.post("/criterios", criteriosController::guardarCriterio, Router.engine);
        Spark.post("/asociarOperacion", asociacionOperacionesController::ejecutarVinculacion, Router.engine);

        //Paginas una vez logueado GET para usuario ADMIN
        Spark.get("/altaUsuario",darAltaUsuarioController::tiposDeUsuarios, Router.engine);
        Spark.get("/listadoDeUsuarios",listarUsuariosController::listarUsuarios,Router.engine);

        // Delete para eliminar un usuario
        Spark.delete("/listadoDeUsuario/eliminar/:id", listarUsuariosController::eliminar,Router.engine);
        Spark.post("/altaUsuario/alta/:id", darAltaUsuarioController::darAltaUsuarioInhabilitado,Router.engine);

        //Guardar los datos de las pestañas POST
        Spark.post("/altaUsuario",darAltaUsuarioController::guardarAltaDeUsuario,Router.engine);
    }
}
