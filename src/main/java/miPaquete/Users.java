package miPaquete;

public class Users {

    String user;
    String password;
    String tipo;

    // Constructor
    public Users(String usuario, String contrasenia, String tipoUser){
        user = usuario;
        password = contrasenia;
        tipo = tipoUser;
    }

    public static void generarUsersDePrueba(){
        UserList.crearNuevoUsuario("Nachiten","zxcpkazxm","Admin");
        UserList.crearNuevoUsuario("Noelia","abcdef","Estandar");
        UserList.crearNuevoUsuario("Nicolas","holache","Estandar");
        UserList.crearNuevoUsuario("Maxi","unaContra","Admin");
        UserList.crearNuevoUsuario("Carlos","otraContra","Admin");
    }
}


