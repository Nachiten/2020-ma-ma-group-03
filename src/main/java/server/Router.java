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

        //Se verifica que el usuario exista
        Spark.post("/usuario", inicioController::loginUsuario, Router.engine);

        //página que muestra error 404 cuando tratan de acceder a esta url sin loguearse
        Spark.get("/usuario", inicioController::error404, Router.engine);


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
        Spark.post("/operacionIngreso", entidadesController::guardarOperacionDeIngreso);
        Spark.post("/presupuesto", entidadesController::guardarPresupuesto);

        // OLD
        Spark.get("/loginCorrecto", (request, response) -> "Login Correcto");
        Spark.get("/loginIncorrecto", (request, response) -> "Login incorrecto");
        Spark.get("/error", (request, response) -> "Ha habido un error inesperado");

    }
}
