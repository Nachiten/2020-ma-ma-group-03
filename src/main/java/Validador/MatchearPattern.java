package Validador;

import java.nio.charset.StandardCharsets;

public class MatchearPattern {

    public static boolean mathearUnPattern(String password, String pattern, int cantMayusculasABuscar){
        char unaClave;

        int cantMayusculasEncontradas = 0;

        for (byte i : password.getBytes(StandardCharsets.UTF_8) ) {
            unaClave = (char)i;
            String passValue = String.valueOf(unaClave);

            if (passValue.matches(pattern)) {
                cantMayusculasEncontradas++;

                if (cantMayusculasEncontradas == cantMayusculasABuscar){
                    return true;
                }
            }
        }
        return false;
    }
}
