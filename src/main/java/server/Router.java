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
        UsuarioController usuarioController = new UsuarioController(contextoDeUsuarioLogueado);
        InicioController inicioController 	= new InicioController(contextoDeUsuarioLogueado);
        EntidadesController entidadesController = new EntidadesController(contextoDeUsuarioLogueado);
        DarAltaUsuarioController darAltaUsuarioController = new DarAltaUsuarioController(contextoDeUsuarioLogueado);
        ListarUsuariosController listarUsuariosController = new ListarUsuariosController(contextoDeUsuarioLogueado);

        // Pagina iniciar sesión
        Spark.get("/", inicioController::inicio, Router.engine);

        // Cerrar sesion
        Spark.post("/", usuarioController::cerrarSesion, Router.engine);

        // Se verifica que el usuario exista
        Spark.post("/inicio", usuarioController::loginUsuario, Router.engine);


        // Paginas una vez logueado GET para usuarios ESTANDAR
        Spark.get("/ingresos", entidadesController::ingresos, Router.engine);
        Spark.get("/egresos", entidadesController::egresos, Router.engine);
        Spark.get("/presupuestos", entidadesController::presupuestos, Router.engine);
        Spark.get("/criterios", entidadesController::criterios, Router.engine);
        Spark.get("/listadoOperaciones", entidadesController::listadoOperaciones, Router.engine);
        Spark.get("/asociarOperacion", entidadesController::asociarOperacion, Router.engine);
        Spark.get("/mensajes", entidadesController::mensajes, Router.engine);
        Spark.get("/inicio", entidadesController::principal, Router.engine);

        // Guardar los datos de las ventanas POST
        Spark.post("/egresos", entidadesController::guardarOperacionDeEgreso, Router.engine);
        Spark.post("/ingresos", entidadesController::guardarOperacionDeIngreso, Router.engine);
        Spark.post("/presupuestos", entidadesController::guardarPresupuesto, Router.engine);
        Spark.post("/criterios", entidadesController::guardarCriterio, Router.engine);
        Spark.post("/asociarOperacion", entidadesController::ejecutarVinculacion, Router.engine);

        //Paginas una vez logueado GET para usuario ADMIN
        Spark.get("/altaUsuario",darAltaUsuarioController::tiposDeUsuarios, Router.engine);
        Spark.get("/listadoDeUsuarios",listarUsuariosController::listarUsuarios,Router.engine);

        // Delete para eliminar un usuario
        Spark.delete("/usuario/:id", usuarioController::eliminar);


        // TODO | Borrar, para probar
        Spark.get("/testLink", (req, res) -> "La pagina funciona capo");

    }
}
