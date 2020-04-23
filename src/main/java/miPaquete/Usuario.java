package miPaquete;

// IDEA PARA LOS USUARIOS | NO IMPLEMENTADA EN NINGUN LADO By: Nachiten
public class Usuario{

    static UsuarioEstandar crearUsuarioEstandar(String unUser, String unaContra){
        return new UsuarioEstandar(unUser, unaContra);
    }

    static UsuarioAdmin crearUsuarioAdmin(String unUser, String unaContra){
        return new UsuarioAdmin(unUser, unaContra);
    }
}

class UsuarioEstandar extends Usuario {
    String user;
    String password;

    UsuarioEstandar(String unUsuario, String unaContra){
        user = unUsuario;
        password = unaContra;
    }

    boolean checkearSiTengoPermisosDe(String unaAccion){
        return true;
    }

}

class UsuarioAdmin extends Usuario {
    String user;
    String password;

    UsuarioAdmin(String unUsuario, String unaContra){
        user = unUsuario;
        password = unaContra;
    }

    boolean checkearSiTengoPermisosDe(String unaAccion){
        return true;
    }
}
