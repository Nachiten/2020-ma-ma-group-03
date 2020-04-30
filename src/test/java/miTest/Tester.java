package miTest;

import miPaquete.ValidadorCredenciales;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tester {
    List<String> comunes = new ArrayList<>(Arrays.asList("password","123456","12345678","1234","qwerty","holaaaaa","Hola123","HolaAAAA","HolaAAAA","comoestas123"));

    @Test
    public void contraseniasNOSeguras(){
        Assert.assertFalse(ValidadorCredenciales.checkearContraseniaSegura("holaaaaa", comunes)); // No tiene ni mayus ni nuimero
        Assert.assertFalse(ValidadorCredenciales.checkearContraseniaSegura("Hola123", comunes)); // No son 8 caracteres
        Assert.assertFalse(ValidadorCredenciales.checkearContraseniaSegura("HolaAAAA", comunes)); // Falta numero
        Assert.assertFalse(ValidadorCredenciales.checkearContraseniaSegura("comoestas123", comunes)); // Falta mayus
    }

    @Test
    public void contraseniasSeguras(){
        Assert.assertTrue(ValidadorCredenciales.checkearContraseniaSegura("HolA12345", comunes));
        Assert.assertTrue(ValidadorCredenciales.checkearContraseniaSegura("chAu2138", comunes));
        Assert.assertTrue(ValidadorCredenciales.checkearContraseniaSegura("queTePa7a", comunes));
    }

    @Test
    public void tieneEspacios(){
        Assert.assertTrue(ValidadorCredenciales.tieneEspacios("hola "));
        Assert.assertTrue(ValidadorCredenciales.tieneEspacios(" 123"));
        Assert.assertTrue(ValidadorCredenciales.tieneEspacios("1234 567"));
    }

    @Test
    public void noTieneEspacios(){
        Assert.assertFalse(ValidadorCredenciales.tieneEspacios("bernardo123"));
        Assert.assertFalse(ValidadorCredenciales.tieneEspacios("juan4567"));
        Assert.assertFalse(ValidadorCredenciales.tieneEspacios("contraseniaSinEspacios"));
    }
}
