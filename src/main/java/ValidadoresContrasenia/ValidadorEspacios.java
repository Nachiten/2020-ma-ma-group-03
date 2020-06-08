package ValidadoresContrasenia;

public class ValidadorEspacios extends Validador {

    public boolean tieneEspacios(String contrasenia){

    return contrasenia.contains(" ");
}

    @Override
    public boolean validarContrasenia(String contrasenia){
        return !tieneEspacios(contrasenia);
    }
}
