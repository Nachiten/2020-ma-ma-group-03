package server;

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
        UsuarioController usuarioController = new UsuarioController();

        InicioController inicioController 	= new InicioController();

        Spark.get("/", inicioController::inicio, Router.engine);
        //prueba
        //Spark.get("/saludo", (request, response) ->"Hola");
        //Spark.get("/saludo",((request, response) -> "Hola "  + request.queryParams("nombre")));


    }
}
