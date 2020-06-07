package Validador;

public class ValidarMayusculas extends Validacion {
    int cantMayusculas;

    public ValidarMayusculas(int cantidadMayusculas){
        cantMayusculas = cantidadMayusculas;
    }

    @Override
    public boolean validarContra(String password){
        return MatchearPattern.mathearUnPattern(password, "[A-Z]", cantMayusculas);
    }


}
