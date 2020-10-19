package validadoresContrasenia;

public class ValidadorMayusculas extends Validador {
    private int cantidadMayusculas;

    public ValidadorMayusculas(int cantidadMayusculas){
        this.cantidadMayusculas = cantidadMayusculas;
    }

    @Override
    public boolean validarContrasenia(String contrasenia){
        return MatchearPattern.mathearUnPattern(contrasenia, "[A-Z]", cantidadMayusculas);
    }
}
