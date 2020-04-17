package miPaquete;

// IDEA PARA LOS USUARIOS
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
    static int unNumero = 3;

    UsuarioEstandar(String unUsuario, String unaContra){
        user = unUsuario;
        password = unaContra;
    }

    boolean checkearSiTengoPermisosDe(String unaAccion){
        return true;
    }

    int metodoPrueba(){
        return unNumero + 3;
    }
}

class UsuarioAdmin extends Usuario{
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
