package miPaquete;

import java.util.Scanner;
import java.util.regex.Matcher; // Esto es para usar expresiones regulares (usado en verSiTieneMayusculas)
import java.util.regex.Pattern;

class CrearUsuario{

    public static void crearNuevoUsuario(){

        // Lector de consola
        Scanner lectorPantalla = new Scanner(System.in);

        int decision;

        do {
            System.out.println("Elija Opcion [0]: Volver; [1]: Crear usuario Estandar; [2]: Crear Usuario Admin \n Inserte el numero: ");
            decision = lectorPantalla.nextInt();
        } while( !(decision == 1 || decision == 2 || decision == 0) );

        if (decision == 0) Sistema.menuPrincipal();

        String nuevoNombre = pedirNombre();
        String nuevaPassword = pedirPassword();

        String tipoUsuario = "Estandar";

        if (decision == 2) tipoUsuario = "Admin";

        UserList.crearNuevoUsuario(nuevoNombre, nuevaPassword, tipoUsuario);
        UserList.cheackearCreacionUsuario(nuevoNombre);

        // Volver al menu principal
        Sistema.menuPrincipal();
    }

    static String pedirNombre(){
        Scanner lectorPantalla = new Scanner(System.in);
        System.out.println("Insertar Usuario: ");
        return lectorPantalla.nextLine();
    }

    static String pedirPassword(){

        String password;
        String password2 = "0";

        do {
            Scanner lectorPantalla = new Scanner(System.in);
            System.out.println("Insertar Contraseña: ");
            password = lectorPantalla.nextLine();

            if (!checkearContrasenia(password)){
                System.out.println("Esta contraseña no es segura.. intente otra.. ");
                continue;
            }

            System.out.println("Insertar Contraseña Nuevamente (para asegurar 0 errores): ");
            password2 = lectorPantalla.nextLine();

        } while (!password.equals(password2));

        return password;
    }

    private static boolean checkearContrasenia(String password) {
        return compararContrasMasComunes(password) && verSiTieneMayusculas(password);
    }

    private static boolean compararContrasMasComunes(String password){
        String[] contrasMasComunes = { "password" , "123456" , "12345678" , "1234", "qwerty", "12345", "dragon", "baseball", "football", "letmein", "0" };

        for (String unaContra : contrasMasComunes) {
            if ( unaContra.equals(password) ) {
                return false;
            }
        }
        return true;
    }

    private static boolean verSiTieneMayusculas(String password){
        // En desarrollo
        return true;
    }

}