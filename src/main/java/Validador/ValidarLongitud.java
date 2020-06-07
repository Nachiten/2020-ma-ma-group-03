package Validador;

public class ValidarLongitud extends Validacion {

    int cantCaracteres;

    public ValidarLongitud(int unaLongitud){
        cantCaracteres = unaLongitud;
    }

    @Override
    public boolean validarContra(String unaContra){
        return unaContra.length() >= cantCaracteres;
    }
}
