package Validador;

public class ValidarNoEspacios extends Validacion {

    public boolean validarContra(String password){
        return !tieneEspacios(password);
    }

    public boolean tieneEspacios(String password){
        int i = 0;

        while ((i < password.length())) {
            if (password.charAt(i) != ' ') {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
}
