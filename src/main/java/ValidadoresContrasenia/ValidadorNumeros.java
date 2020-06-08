package ValidadoresContrasenia;

public class ValidadorNumeros extends Validador {

    int cantidadNumeros;

    public ValidadorNumeros(int cantidadNumeros){
        this.cantidadNumeros = cantidadNumeros;
    }

    @Override
    public boolean validarContrasenia(String contrasenia){
        return MatchearPattern.mathearUnPattern(contrasenia, "[0-9]", cantidadNumeros);
    }
}