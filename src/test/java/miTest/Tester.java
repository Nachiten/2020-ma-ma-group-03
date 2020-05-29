package miTest;

import Usuarios.TipoUsuario;
import Usuarios.Usuario;
import Validador.ValidadorCredenciales;
import org.junit.Assert;
import org.junit.Test;

public class Tester {

    Usuario miUsuario1 = new Usuario(TipoUsuario.ESTANDAR, "Nachiten", "zxcpkazxm123ABC");
    Usuario miUsuario2 = new Usuario(TipoUsuario.ESTANDAR, "Noe", "abcde123ABC");
    Usuario miUsuario3 = new Usuario(TipoUsuario.ADMIN, "Carlos", "aaaaa123ABC");
    Usuario miUsuario4 = new Usuario(TipoUsuario.ADMIN, "Maxi", "cosas123ABC");

    @Test
    public void probarTiposUsuario(){
        Assert.assertSame(miUsuario1.tipo, TipoUsuario.ESTANDAR);
        Assert.assertSame(miUsuario2.tipo, TipoUsuario.ESTANDAR);
        Assert.assertSame(miUsuario3.tipo, TipoUsuario.ADMIN);
        Assert.assertSame(miUsuario4.tipo, TipoUsuario.ADMIN);
    }

    @Test
    public void probarUsuarios(){
        Assert.assertSame(miUsuario1.user, "Nachiten");
        Assert.assertSame(miUsuario2.user, "Noe");
        Assert.assertSame(miUsuario3.user, "Carlos");
        Assert.assertSame(miUsuario4.user, "Maxi");
    }

    @Test
    public void probarContrasenias(){
        Assert.assertSame(miUsuario1.password,"zxcpkazxm123ABC");
        Assert.assertSame(miUsuario2.password,"abcde123ABC");
        Assert.assertSame(miUsuario3.password,"aaaaa123ABC");
        Assert.assertSame(miUsuario4.password,"cosas123ABC");
    }


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
    public void tieneMasDe8Caracteres(){
        Assert.assertTrue(ValidadorCredenciales.esSegura("HolA12345"));
        Assert.assertTrue(ValidadorCredenciales.esSegura("chAu2138"));
        Assert.assertTrue(ValidadorCredenciales.esSegura("queTePa7a"));
    }

    @Test
    public void noTieneMasDe8Caracteres(){
        Assert.assertFalse(ValidadorCredenciales.esSegura("Ho45r6u"));
        Assert.assertFalse(ValidadorCredenciales.esSegura("ch8"));
        Assert.assertFalse(ValidadorCredenciales.esSegura("qu"));
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
