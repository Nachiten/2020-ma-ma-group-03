package ValidadoresContrasenia;

public class ValidadorLongitud extends Validador {

    private int cantidadCaracteres;

    public ValidadorLongitud(int longitud){
        cantidadCaracteres = longitud;
    }

    @Override
    public boolean validarContrasenia(String contrasenia){
        return contrasenia.length() >= cantidadCaracteres;
    }
}