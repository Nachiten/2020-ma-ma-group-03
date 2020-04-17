package miPaquete;

import java.util.Scanner;

public class Principal {

    public static void main(String[ ] args) {
        menuPrincipal();
    }

    public static void menuPrincipal(){

        // Lector de consola
        Scanner lectorPantalla = new Scanner(System.in);

        // Menu principal
        int decision;

        System.out.println("Elija Opción: Salir = 0; Crear usuario = 1; Iniciar Sesión = 2 \n Inserte un número: ");
        decision = lectorPantalla.nextInt();

        if (decision != 0 && decision!= 1 && decision != 2){
            System.out.println("No ingresó un número válido, por favor ingrese correctamente un número.");
            menuPrincipal();
        }

        switch(decision){
            case 0:
                System.exit(1);  // | 1 = Cerrado por el usuario
                break;
            case 1:
                UserHandler.iniciarCrearNuevoUsuario();
                break;
            case 2:
                UserHandler.iniciarLogin();
                break;
        }

    }

    // Exit | 0 = Salio por aca
}


