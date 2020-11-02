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
        UsuarioController usuarioController = new UsuarioController();
        InicioController inicioController 	= new InicioController();
        EntidadesController entidadesController = new EntidadesController();
        DarAltaUsuarioController darAltaUsuarioController = new DarAltaUsuarioController();
        ListarUsuariosController listarUsuariosController = new ListarUsuariosController();

        // Pagina root, inicio
        Spark.get("/", inicioController::inicio, Router.engine);

        // Cerrar sesion
        Spark.post("/", usuarioController::cerrarSesion, Router.engine);

        // Se verifica que el usuario exista
        Spark.post("/usuario", entidadesController::login, Router.engine);

        // Página que muestra error 404 cuando tratan de acceder a esta url sin loguearse
        Spark.get("/usuario", inicioController::error404, Router.engine);

        // Paginas una vez logueado GET
        Spark.get("/principal", entidadesController::principal, Router.engine);
        Spark.get("/ingresos", entidadesController::ingresos, Router.engine);
        Spark.get("/egresos", entidadesController::egresos, Router.engine);
        Spark.get("/presupuestos", entidadesController::presupuestos, Router.engine);
        Spark.get("/criterios", entidadesController::criterios, Router.engine);
        Spark.get("/listadoOperaciones", entidadesController::listadoOperaciones, Router.engine);
        Spark.get("/asociarOperacion", entidadesController::asociarOperacion, Router.engine);
        Spark.get("/mensajes", entidadesController::mensajes, Router.engine);

        // Guardar los datos de las ventanas POST
        Spark.post("/egresos", entidadesController::guardarOperacionDeEgreso, Router.engine);
        Spark.post("/ingresos", entidadesController::guardarOperacionDeIngreso, Router.engine);
        Spark.post("/presupuestos", entidadesController::guardarPresupuesto, Router.engine);
        Spark.post("/criterios", entidadesController::guardarCriterio, Router.engine);
        Spark.post("/asociarOperacion", entidadesController::ejecutarVinculacion, Router.engine);

        //
        Spark.get("/altaUsuario",darAltaUsuarioController::altaUsuario,Router.engine);


        //
        Spark.get("/listadoDeUsuarios",listarUsuariosController::listarUsuarios,Router.engine);


    }
}
