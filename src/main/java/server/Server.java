package server;


import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
    public static void main(String[] args) {
        Spark.port(getHerokuAssignedPort());
        Router.init();
        DebugScreen.enableDebugScreen();
        System.out.println("Estas en el puerto [" + getHerokuAssignedPort() + "]");
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        // Puerto default en caso de estar en localHost
        return 9000;
    }
}
