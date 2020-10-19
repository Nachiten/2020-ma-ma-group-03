package validadoresContrasenia;

public class ValidadorNumeros extends Validador {

    private int cantidadNumeros;

    public ValidadorNumeros(int cantidadNumeros){
        this.cantidadNumeros = cantidadNumeros;
    }

    @Override
    public boolean validarContrasenia(String contrasenia){
        return MatchearPattern.mathearUnPattern(contrasenia, "[0-9]", cantidadNumeros);
    }
}