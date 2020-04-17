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

    /*
    public static void generarUsersDePrueba(){
        UserHandler.crearUsuario("Nachiten","zxcpkazxm","Admin");
        UserHandler.crearUsuario("Noelia","abcdef","Estandar");
        UserHandler.crearUsuario("Nicolas","holache","Estandar");
        UserHandler.crearUsuario("Maxi","unaContra","Admin");
        UserHandler.crearUsuario("Carlos","otraContra","Admin");
    }
    */ // Esto va en un test, no aca
}





