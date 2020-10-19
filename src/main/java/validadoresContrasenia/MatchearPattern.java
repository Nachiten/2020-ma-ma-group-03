package validadoresContrasenia;

import java.nio.charset.StandardCharsets;

public class MatchearPattern {

    public static boolean mathearUnPattern(String contrasenia, String pattern, int cantidadABuscar){
        char unaClave;

        int cantidadElementosEncontrados = 0;

        for (byte i : contrasenia.getBytes(StandardCharsets.UTF_8) ) {
            unaClave = (char)i;
            String passValue = String.valueOf(unaClave);

            if (passValue.matches(pattern)) {
                cantidadElementosEncontrados++;
            }
        }
        return cantidadElementosEncontrados >= cantidadABuscar;
    }
}