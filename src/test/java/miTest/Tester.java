package miTest;

import miPaquete.ValidadorCredenciales;
import org.junit.Assert;
import org.junit.Test;

public class Tester {
    @Test
    public void contraseniasNOSeguras(){
        Assert.assertFalse(ValidadorCredenciales.esSegura("holaaaaa")); // No tiene ni mayus ni nuimero
        Assert.assertFalse(ValidadorCredenciales.esSegura("Hola123")); // No son 8 caracteres
        Assert.assertFalse(ValidadorCredenciales.esSegura("HolaAAAA")); // Falta numero
        Assert.assertFalse(ValidadorCredenciales.esSegura("comoestas123")); // Falta mayus
    }

    @Test
    public void contraseniasSeguras(){
        Assert.assertTrue(ValidadorCredenciales.esSegura("HolA12345"));
        Assert.assertTrue(ValidadorCredenciales.esSegura("chAu2138"));
        Assert.assertTrue(ValidadorCredenciales.esSegura("queTePa7a"));
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
