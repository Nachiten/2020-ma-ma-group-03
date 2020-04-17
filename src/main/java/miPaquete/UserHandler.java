package miPaquete;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserHandler {

    // Lista de usuarios del sistema [Deberian ser guardados en disco]
    public static List<User> listaUsuarios = new ArrayList<>();

    public static void iniciarLogin() {

        // Iniciar LogIN

        Scanner lectorPantalla = new Scanner(System.in);

        //boolean loginSuccess = false;

        String user;
        System.out.println("Insertar Usuario: ");
        user = lectorPantalla.nextLine();

        // Corrobar si el nombre existe [Caso contrario vuelve al comienzo]
        if ( !encontrarUsuario(user) ){
            System.out.println("Este usuario no existe!!! Intenta nuevamente");
            iniciarLogin();
        }

        String contrasenia;
        System.out.println("Cual es la contraseña?: ");
        contrasenia = lectorPantalla.nextLine();

        if (!checkearContrasenia(contrasenia, user) ){
            System.out.println("Credenciales Incorrectas.... por favor vuelva a intentarlo");
            iniciarLogin();
        }

        System.out.println("Credenciales ACEPTADAS :D");
        // Volver al menu principal luego de loguearse | Aca se debe acceder al sistema
        Principal.menuPrincipal();
    }

    public static void iniciarCrearNuevoUsuario(){

        // Lector de consola
        Scanner lectorPantalla = new Scanner(System.in);

        System.out.println("Elija Opción: Volver = 0; Crear usuario Estándar = 1; Crear Usuario Admin = 2 \n Inserte un número: ");
        int decision = lectorPantalla.nextInt();

        // Checkeo de numero valido
        if (decision != 0 && decision!= 1 && decision != 2){
            System.out.println("No ingresó un número válido, por favor ingrese correctamente un número.");
            iniciarCrearNuevoUsuario();
        }

        if (decision == 0) Principal.menuPrincipal();

        String nuevoNombre = ValidadorCredenciales.pedirNombre();
        String nuevaPassword = ValidadorCredenciales.pedirPassword();

        String tipoUsuario = "Estandar";

        if (decision == 2) tipoUsuario = "Admin";
        crearUsuario(nuevoNombre, nuevaPassword, tipoUsuario);

        if (encontrarUsuario(nuevoNombre)){
            System.out.println("El usuario de nombre: " + nuevoNombre + " fue creado correctamente. Tipo: " + tipoUsuario);
        } else {
            System.out.println("El usuario no fue creado correctamente");
            // Usar excepcion aca
        }

        // Volver al menu principal
        Principal.menuPrincipal();
    }

    private static void crearUsuario(String usuario, String contrasenia, String tipoUser){
        listaUsuarios.add( new User(usuario, contrasenia, tipoUser) );
    }

    private static boolean encontrarUsuario(String unNombre){

        for ( User unUser : listaUsuarios ) {
            if ( unUser.user.equals(unNombre) ) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkearContrasenia(String laPassword, String usuario){

        User elUsuario = new User("0","0","0");

        for (User unUser : listaUsuarios){
            // Encuentro el usuario de nombre "usuario"
            if ( unUser.user.equals(usuario) ) {
                elUsuario = unUser;
                break;
            }
        }

        // Este checkeo es innecesario ya que el usuario viene incializado ... LogIN linea 20
        if (elUsuario.tipo.equals("0")){
            return false;
        }

        // Retornar si la contra es correcta o no
        return elUsuario.password.equals(laPassword);
    }

}
