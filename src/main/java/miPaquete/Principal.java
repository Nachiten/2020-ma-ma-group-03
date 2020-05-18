package miPaquete;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        pedirContrasenia();

        // Exit | 0 = Salió por aca
    }

    private static void pedirContrasenia(){
        Scanner lectorPantalla = new Scanner(System.in);
        System.out.println("Insertar Contraseña: ");
        String password = lectorPantalla.nextLine();

        if (!ValidadorCredenciales.esSegura(password)){
            System.out.println("Elija otra contraseña, esta contraseña no es segura. Debe tener: Al menos una mayúscula; 8 caracteres; Al menos un número.");

            pedirContrasenia();
        }

        if (ValidadorCredenciales.tieneEspacios(password)){
            System.out.println("Elija otra contraseña. La contraseña no puede tener espacios.");
            pedirContrasenia();
        }

    }
}


