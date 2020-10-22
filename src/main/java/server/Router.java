package server;

import domain.controllers.InicioController;
import domain.controllers.OperacionEgresoController;
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
        UsuarioController usuarioController = new UsuarioController();

        InicioController inicioController 	= new InicioController();

        OperacionEgresoController operacionEgresoController = new OperacionEgresoController();

        // Pagina root, inicio
        Spark.get("/", inicioController::inicio, Router.engine);

        // Login luego de pasar por inicio
        Spark.post("/login", inicioController::login);

        Spark.get("/principal", inicioController::principal, Router.engine);
        // Paginas una vez logueado
        Spark.get("/ingresos", inicioController::ingresos, Router.engine);
        Spark.get("/egresos", inicioController::egresos, Router.engine);
        Spark.get("/presupuestos", inicioController::presupuestos, Router.engine);
        Spark.get("/criterios", inicioController::criterios, Router.engine);
        Spark.get("/listadoOperaciones", inicioController::listadoOperaciones, Router.engine);
        Spark.get("/asociarOperacion", inicioController::asociarOperacion, Router.engine);
        // Falta implementar hbs
        //Spark.get("/mensajes", inicioController::presupuestos, Router.engine);

        // Post para guardar operacion de egreso
        Spark.post("/operacionEgreso", operacionEgresoController::guardarOperacionDeEgreso);

        Spark.get("/loginCorrecto", (request, response) -> "Login Correcto");
        Spark.get("/loginIncorrecto", (request, response) -> "Login incorrecto");
        Spark.get("/error:codError", (request, response) -> "El error fue: " + request.queryParams("codError"));

    }
}
