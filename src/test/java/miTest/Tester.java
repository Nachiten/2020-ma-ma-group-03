package miTest;

import org.junit.Assert;
import org.junit.Test;

public class Tester {

    @Test
    public void test1(){
        Assert.assertEquals(1,1);

    }

    @Test
    public void verSiTieneMayuscula(){
        String password = "hola";

        char clave;
        int contador = 0;
        for (byte i = 0; i < password.length(); i++) {
            clave = password.charAt(i);
            String passValue = String.valueOf(clave);
            if (passValue.matches("[A-Z]")) {
                contador++;
            }
        }
        if(contador > 0){
            System.out.println("Tiene al menos una mayuscula");
        }else{
        System.out.println("NO tiene mayuscula");}
    }

    @Test
    public void cantidadDeLetras(){
        String palabra = "Hola123";
        if(palabra.length()>=8){
            System.out.println("Tiene al menos 8 caracteres");
        }else{System.out.println("No tiene 8 caracteres");}
    }
}
