package miPaquete;

public class User {

    String user;
    String password;
    String tipo;

    // Constructor
    public User(String usuario, String contrasenia, String tipoUser){
        user = usuario;
        password = contrasenia;
        tipo = tipoUser;
    }

    public static void generarUsersDePrueba(){
        UserHandler.crearNuevoUsuario("Nachiten","zxcpkazxm","Admin");
        UserHandler.crearNuevoUsuario("Noelia","abcdef","Estandar");
        UserHandler.crearNuevoUsuario("Nicolas","holache","Estandar");
        UserHandler.crearNuevoUsuario("Maxi","unaContra","Admin");
        UserHandler.crearNuevoUsuario("Carlos","otraContra","Admin");
    }
}





