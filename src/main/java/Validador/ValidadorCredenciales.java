package Validador;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidadorCredenciales {

    /* No va de esta menra
    public static boolean checkearSeguridad(String password){
        String password2;

        System.out.println("Insertar Contraseña Nuevamente (para asegurar 0 errores): ");
        password2 = lectorPantalla.nextLine();

        if(!password.equals(password2)) {
            System.out.println("Las contraseñas ingresadas no son iguales, ingrese dos nuevas contraseñas iguales. ");
            return password;
        }

        return password;
    }*/

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

    public static boolean esSegura(String password) {
        return verSiTieneMayusculas(password) && verSiTieneNumeros(password) && tieneAlMenosOchoCaracteres(password) && !esComun(password);
    }

    private static boolean verSiTieneMayusculas(String password){
        return mathearPattern(password, "[A-Z]");
    }

    private static boolean verSiTieneNumeros(String password){
        return mathearPattern(password, "[0-9]");
    }

    private static boolean tieneAlMenosOchoCaracteres(String password){ return password.length() >= 8; }

    private static boolean esComun(String password){
        List<String> comunes = new ArrayList<>(Arrays.asList("password","123456","12345678","1234","qwerty"));

        return comunes.contains(password);
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

    /* Para mas adelante
    public boolean rotar(LocalDate fecha1, LocalDate fecha2) {
        return fecha1.equals(fecha2);
    }*/
}
