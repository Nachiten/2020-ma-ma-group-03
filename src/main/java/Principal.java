import Usuarios.TipoUsuario;
import Usuarios.Usuario;
import Validador.ValidadorCredenciales;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Usuario miUsuario = crearUsuario();

        System.out.println("El usuario nuevo creado es de tipo: " + miUsuario.tipo + " tiene nombre: " + miUsuario.user + " y la contraseña es: " + miUsuario.password);

        // Exit | 0 = Esta bien
    }

    static Usuario crearUsuario(){
        TipoUsuario tipoUser = pedirTipoUsuario();
        String user = pedirUsuario();
        String password = pedirContrasenia();

        return new Usuario(tipoUser, user, password);
    }

    static TipoUsuario pedirTipoUsuario(){
        Scanner lectorPantalla = new Scanner(System.in);

        System.out.println("Seleccionar tipo de usuario a crear: [1] Admin [2] Estandar");

        int codigoUsuario = lectorPantalla.nextInt();

        if (codigoUsuario == 1){
            return TipoUsuario.ADMIN;
        } else if (codigoUsuario == 2){
            return TipoUsuario.ESTANDAR;
            // Codigo no valido
        } else {
            return pedirTipoUsuario();
        }
    }

    static String pedirUsuario(){
        Scanner lectorPantalla = new Scanner(System.in);
        System.out.println("Ingresar nombre de usuario: ");
        return lectorPantalla.nextLine();
    }

    private static String pedirContrasenia(){
        Scanner lectorPantalla = new Scanner(System.in);

        System.out.println("Insertar Contraseña: ");
        String password = lectorPantalla.nextLine();

        if (!ValidadorCredenciales.esSegura(password)){
            System.out.println("Elija otra contraseña, esta contraseña no es segura. Debe tener: Al menos una mayúscula; 8 caracteres; Al menos un número.");

            password = pedirContrasenia();
            return password;
        }

        if (ValidadorCredenciales.tieneEspacios(password)){
            System.out.println("Elija otra contraseña. La contraseña no puede tener espacios.");
            password = pedirContrasenia();
            return password;
        }

        return password;
    }
}


