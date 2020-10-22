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

        // Login luego de pasar por inicio
        Spark.post("/login", inicioController::login);

        //Spark.get("/principal", inicioController::principal, Router.engine);

        // Paginas una vez logueado GET
        Spark.get("/ingresos", entidadesController::ingresos, Router.engine);
        Spark.get("/egresos", entidadesController::egresos, Router.engine);
        Spark.get("/presupuestos", entidadesController::presupuestos, Router.engine);
        Spark.get("/criterios", entidadesController::criterios, Router.engine);
        Spark.get("/listadoOperaciones", entidadesController::listadoOperaciones, Router.engine);
        Spark.get("/asociarOperacion", entidadesController::asociarOperacion, Router.engine);
        // Falta implementar hbs
        //Spark.get("/mensajes", inicioController::presupuestos, Router.engine);

        // Guardar los datos de las ventanas POST
        Spark.post("/operacionEgreso", entidadesController::guardarOperacionDeEgreso);

        // OLD
        Spark.get("/loginCorrecto", (request, response) -> "Login Correcto");
        Spark.get("/loginIncorrecto", (request, response) -> "Login incorrecto");
        Spark.get("/error:codError", (request, response) -> "El error fue: " + request.queryParams("codError"));

    }
}
