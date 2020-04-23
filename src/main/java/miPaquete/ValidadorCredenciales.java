package miPaquete;

import java.util.Scanner;

public class ValidadorCredenciales {

    public static String pedirPassword(){

        String password;
        String password2;

        Scanner lectorPantalla = new Scanner(System.in);
        System.out.println("Insertar Contraseña: ");
        password = lectorPantalla.nextLine();

        if (!checkearContraseniaSegura(password)){
            System.out.println("Esta contraseña no es segura... Intente con al menos una mayúscula, 8 letras y un número. ");
            password = pedirPassword();
            return password;
        }
        if (tieneEspacios(password)){
            System.out.println("La contraseña no puede contener espacios en blanco");
            password = pedirPassword();
            return password;
        }

        System.out.println("Insertar Contraseña Nuevamente (para asegurar 0 errores): ");
        password2 = lectorPantalla.nextLine();

        if(!password.equals(password2)) {
            System.out.println("Las contraseñas ingresadas no son iguales, ingrese dos nuevas contraseñas iguales. ");
            password = pedirPassword();
            return password;
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
        return compararContrasMasComunes(password) && verSiTieneMayusculas(password) && verSiTieneNumeros(password) && tieneAlMenosOchoLetras(password);
    }

    private static boolean compararContrasMasComunes(String password){
        String[] contrasMasComunes = { "password" , "123456" , "12345678" , "1234", "qwerty", "12345", "dragon", "baseball", "football", "letmein", "0", "hola1234" };

        for (String unaContra : contrasMasComunes) {
            if ( unaContra.equals(password) ) {
                return false;
            }
        }
        return true;
    }

    private static boolean verSiTieneMayusculas(String password){
        //TODO Hacer esto
        char clave;
        int contador = 0;
        for (byte i = 0; i < password.length(); i++) {
            clave = password.charAt(i);
            String passValue = String.valueOf(clave);
            if (passValue.matches("[A-Z]")) {
                contador++;
            }
        }
        if(contador > 0){
            return true;
        }
        return false;
    }

    private static boolean verSiTieneNumeros(String password){
        //TODO Usar expresiones regulares
            char unaClave;
            int otroContador = 0;
            for (byte i = 0; i < password.length(); i++) {
                unaClave = password.charAt(i);
                String passValue = String.valueOf(unaClave);
                if (passValue.matches("[0-9]")) {
                    otroContador++;
                }
            }
        if(otroContador > 0){
            return true;
        }
        return false;
     }

     private  static  Boolean tieneAlMenosOchoLetras(String password){
         if(password.length()>=8){
             return true;
         }else{
             return false;
         }
     }
}
