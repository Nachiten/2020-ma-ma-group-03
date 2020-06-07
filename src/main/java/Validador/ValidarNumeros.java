package Validador;

public class ValidarNumeros extends Validacion {

    int cantNumeros;

    public ValidarNumeros(int cantidadNumeros){
        cantNumeros = cantidadNumeros;
    }

    @Override
    public boolean validarContra(String password){
        return MatchearPattern.mathearUnPattern(password, "[0-9]", cantNumeros);
    }
}
