package server;

import domain.controllers.EntidadesController;
import domain.controllers.InicioController;
import domain.controllers.UsuarioController;
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

        // Pagina root, inicio
        Spark.get("/", inicioController::inicio, Router.engine);

        // Cerrar sesion
        Spark.post("/", usuarioController::cerrarSesion, Router.engine);

        // Se verifica que el usuario exista
        Spark.post("/usuario", inicioController::loginUsuario, Router.engine);

        // Página que muestra error 404 cuando tratan de acceder a esta url sin loguearse
        Spark.get("/usuario", inicioController::error404, Router.engine);

        // Paginas una vez logueado GET
        Spark.get("/usuario/:id", entidadesController::principal, Router.engine);
        Spark.get("/usuario/ingresos/:id", entidadesController::ingresos, Router.engine);
        Spark.get("/usuario/egresos/:id", entidadesController::egresos, Router.engine);
        Spark.get("/usuario/presupuestos/:id", entidadesController::presupuestos, Router.engine);
        Spark.get("/usuario/criterios/:id", entidadesController::criterios, Router.engine);
        Spark.get("/usuario/listadoOperaciones/:id", entidadesController::listadoOperaciones, Router.engine);
        Spark.get("/usuario/asociarOperacion/:id", entidadesController::asociarOperacion, Router.engine);
        Spark.get("/usuario/mensajes/:id", entidadesController::mensajes, Router.engine);


        // Guardar los datos de las ventanas POST
        Spark.post("/usuario/egresos/:id", entidadesController::guardarOperacionDeEgreso, Router.engine);
        Spark.post("/usuario/ingresos/:id", entidadesController::guardarOperacionDeIngreso, Router.engine);
        Spark.post("/usuario/presupuestos/:id", entidadesController::guardarPresupuesto, Router.engine);
        Spark.post("/usuario/criterios/:id", entidadesController::guardarCriterio, Router.engine);

    }
}
