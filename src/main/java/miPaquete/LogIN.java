package miPaquete;

import java.util.Scanner;


public class LogIN {

    public static void iniciarLogin() {

        // Iniciar LogIN

        Scanner lectorPantalla = new Scanner(System.in);

        boolean loginSuccess = false;

        do {
            String user;
            System.out.println("Insertar Usuario: ");
            user = lectorPantalla.nextLine();

            // Corrobar si el nombre existe [Caso contrario vuelve arriba (al comienzo del do)]
            if (!UserList.checkearUsuarioExiste(user)){
                System.out.println("Este usuario no existe!!! Intenta nuevamente");
                continue;
            }

            String contrasenia;
            System.out.println("Cual es la contraseña?: ");
            contrasenia = lectorPantalla.nextLine();

            if ( UserList.checkearContrasenia(contrasenia, user) ){
                loginSuccess = true;
                System.out.println("Credenciales ACEPTADAS :D");
            } else{
                System.out.println("Credenciales Incorrectas.... por favor vuelva a intentarlo");
            }

        } while (!loginSuccess);

        // Volver al menu principal luego de loguearse
        Sistema.menuPrincipal();
    }
}
