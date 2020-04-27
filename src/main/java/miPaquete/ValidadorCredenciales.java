package miPaquete;

import java.nio.charset.Charset;
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

    public static boolean tieneEspacios(String password){
        int i = 0;

        while ((i < password.length())) {
            if (password.charAt(i) != ' ') {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean checkearContraseniaSegura(String password) {
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
        return mathearPattern(password, "[A-Z]");
    }

    private static boolean verSiTieneNumeros(String password){
        return mathearPattern(password, "[0-9]");
     }

     private static boolean mathearPattern(String password, String pattern){
         char unaClave;

         for (byte i : password.getBytes(Charset.forName("UTF-8")) ) {
             unaClave = (char)i;
             String passValue = String.valueOf(unaClave);
             if (passValue.matches(pattern)) {
                 return true;
             }
         }

         return false;

     }

     private static boolean tieneAlMenosOchoLetras(String password){
        return password.length() >= 8;
     }
}
