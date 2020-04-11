package miPaquete;

import java.util.Scanner;

public class Sistema {

    public static void main(String[ ] args) {
        menuPrincipal();
    }

    public static void menuPrincipal(){

        Users.generarUsersDePrueba();

        // Lector de consola
        Scanner lectorPantalla = new Scanner(System.in);

        // Menu principal
        int decision;

        do {
            System.out.println("Elija Opcion [0]: Salir; [1]: Crear usuario ; [2]: Iniciar Sesion \n Inserte el numero: ");
            decision = lectorPantalla.nextInt();
        } while( !(decision == 1 || decision == 2 || decision == 0) );

        switch (decision){
            case 1:
                CrearUsuario.crearNuevoUsuario();
                break;
            case 2:
                LogIN.iniciarLogin();
                break;
        }

        // Exit
    }
}


