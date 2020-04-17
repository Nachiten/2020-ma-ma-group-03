package miPaquete;

import java.util.Scanner;

public class ValidadorCredenciales {

    static String pedirPassword(){

        String password;
        String password2;

            Scanner lectorPantalla = new Scanner(System.in);
            System.out.println("Insertar Contraseña: ");
            password = lectorPantalla.nextLine();

            if (!checkearContraseniaSegura(password)){
                System.out.println("Esta contraseña no es segura... Intente con otra más larga o menos común. ");
                pedirPassword();
            }
            if (tieneEspacios(password)){
                System.out.println("La contraseña no puede contener espacios en blanco");
                pedirPassword();
            }

            System.out.println("Insertar Contraseña Nuevamente (para asegurar 0 errores): ");
            password2 = lectorPantalla.nextLine();

            if(!password.equals(password2)) {
                System.out.println("Las contraseñas ingresadas no son iguales, ingrese dos nuevas contraseñas iguales. ");
                pedirPassword();
            }

        return password;
    }

    private static boolean tieneEspacios(String password){
        boolean espacios = false;
        int i = 0;

        while ((i < password.length())) {
            if (password.charAt(i) != ' ') {
                i++;
            } else {
                espacios = true;
                break;
            }
        }
        return espacios;
    }

    private static boolean checkearContraseniaSegura(String password) {
        return compararContrasMasComunes(password) && verSiTieneMayusculas(password) && verSiTieneNumeros(password);
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
        //TODO Hacer esto
        return true;
    }

    private static boolean verSiTieneNumeros(String password){
        //TODO Usar expresiones regulares
        return true;
    }

    static String pedirNombre(){
        Scanner lectorPantalla = new Scanner(System.in);
        System.out.println("Insertar nombre de usuario: ");

        String usuario = lectorPantalla.nextLine();

        if (tieneEspacios(usuario)){
            System.out.println("El usuario no puede contener espacios en blanco");
            pedirNombre();
        }

        return usuario;
    }



}
