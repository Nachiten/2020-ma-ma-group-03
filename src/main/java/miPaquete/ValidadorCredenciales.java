package miPaquete;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.Scanner;

public class ValidadorCredenciales {

    public static String pedirPassword(){

        String password;
        String password2;
        List<String> comunes = new ArrayList<>(Arrays.asList("password","123456","12345678","1234","qwerty"));

        Scanner lectorPantalla = new Scanner(System.in);
        System.out.println("Insertar Contraseña: ");
        password = lectorPantalla.nextLine();

        if (!checkearContraseniaSegura(password, comunes)){
            System.out.println("Esta contraseña no es segura... Intente con al menos una mayúscula, 8 caracteres y un número. ");
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

    public static boolean checkearContraseniaSegura(String password, List<String> comunes) {
        return verSiTieneMayusculas(password) && verSiTieneNumeros(password) && tieneAlMenosOchoCaracteres(password) && !esComun(password, comunes);
    }

    private static boolean esComun(String password, List<String> comunes){ return comunes.contains(password); }

    private static boolean verSiTieneMayusculas(String password){
        return mathearPattern(password, "[A-Z]");
    }

    private static boolean verSiTieneNumeros(String password){
        return mathearPattern(password, "[0-9]");
     }

    private static boolean mathearPattern(String password, String pattern){
        char unaClave;

        for (byte i : password.getBytes(StandardCharsets.UTF_8) ) {
            unaClave = (char)i;
            String passValue = String.valueOf(unaClave);

            if (passValue.matches(pattern)) {
                return true;
            }
        }

        return false;

    }

    private static boolean tieneAlMenosOchoCaracteres(String password){
        return password.length() >= 8;
     }

    public boolean rotar(LocalDate fecha1, LocalDate fecha2) {
        return fecha1.equals(fecha2);
    }
}
