package validadoresContrasenia;

import java.util.ArrayList;
import java.util.List;

public class ValidadorCredenciales {
    private List<Validador> validadores;

    public ValidadorCredenciales(List<Validador> validadores){
        this.validadores = new ArrayList<>();
        this.validadores.addAll(validadores);
    }

    public boolean esSegura(String contrasenia) {
       return validadores.stream().allMatch(validacion -> validacion.validarContrasenia(contrasenia));

    }
}
