package ValidadoresContrasenia;

public class ValidadorMayusculas extends Validador {
    int cantidadMayusculas;

    public ValidadorMayusculas(int cantidadMayusculas){
        this.cantidadMayusculas = cantidadMayusculas;
    }

    @Override
    public boolean validarContrasenia(String contrasenia){
        return MatchearPattern.mathearUnPattern(contrasenia, "[A-Z]", cantidadMayusculas);
    }
}
