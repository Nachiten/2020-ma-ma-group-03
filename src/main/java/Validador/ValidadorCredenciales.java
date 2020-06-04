package Validador;

import java.util.ArrayList;
import java.util.List;

public class ValidadorCredenciales {
    List<Validacion> validaciones = new ArrayList<>();

    public ValidadorCredenciales(List<Validacion> lasValidaciones){
        validaciones.addAll(lasValidaciones);
    }

    public boolean esSegura(String password) {

        for(Validacion unaValidacion : validaciones){
            if (!unaValidacion.validarContra(password)){
                return false;
            }
        }
        return true;
    }
}
