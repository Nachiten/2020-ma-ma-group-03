package miTest;

import Usuarios.TipoUsuario;
import Usuarios.Usuario;
import Validador.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tester {

    Usuario miUsuario1 = new Usuario(TipoUsuario.ESTANDAR, "Nachiten", "zxcpkazxm123ABC");
    Usuario miUsuario2 = new Usuario(TipoUsuario.ESTANDAR, "Noe", "abcde123ABC");
    Usuario miUsuario3 = new Usuario(TipoUsuario.ADMIN, "Carlos", "aaaaa123ABC");
    Usuario miUsuario4 = new Usuario(TipoUsuario.ADMIN, "Maxi", "cosas123ABC");

    /*
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
    }*/

    @Test
    public void probarContrasenias(){
        Assert.assertSame(miUsuario1.password,"zxcpkazxm123ABC");
        Assert.assertSame(miUsuario2.password,"abcde123ABC");
        Assert.assertSame(miUsuario3.password,"aaaaa123ABC");
        Assert.assertSame(miUsuario4.password,"cosas123ABC");
    }

    Validacion longitudMayorA8 = new ValidarLongitud(8);
    Validacion tieneUnaMayuscula = new ValidarMayusculas(1);
    Validacion tieneUnNumero = new ValidarNumeros(1);
    Validacion noTieneEspacios = new ValidarNoEspacios();

    ValidadorCredenciales miValidador = new ValidadorCredenciales(Arrays.asList(longitudMayorA8, tieneUnaMayuscula, tieneUnNumero, noTieneEspacios));

    /*

    List<string> optionList = new List<string>

    );
        validaciones.add(new ValidarMayusculas(cantMayusculas));
        validaciones.add(new ValidarNumeros(cantidadNumeros));
        validaciones.add(new ValidarNoEspacios()

            { "AdditionalCardPersonAdressType",  rest of elements  };

     */

    @Test
    public void contraseniasNOSeguras(){
        Assert.assertFalse(miValidador.esSegura("holaaaaa")); // No tiene ni mayus ni nuimero
        Assert.assertFalse(miValidador.esSegura("Hola123")); // No son 8 caracteres
        Assert.assertFalse(miValidador.esSegura("HolaAAAA")); // Falta numero
        Assert.assertFalse(miValidador.esSegura("comoestas123")); // Falta mayus
    }

    @Test
    public void contraseniasSeguras(){
        Assert.assertTrue(miValidador.esSegura("HolA12345"));
        Assert.assertTrue(miValidador.esSegura("chAu2138"));
        Assert.assertTrue(miValidador.esSegura("queTePa7a"));
    }



    // CHECKEAR CARACTERES
    @Test
    public void tieneMasDe8Caracteres(){
        Assert.assertTrue(longitudMayorA8.validarContra("HolA12345"));
        Assert.assertTrue(longitudMayorA8.validarContra("chAu2138"));
        Assert.assertTrue(longitudMayorA8.validarContra("queTePa7a"));
    }

    @Test
    public void noTieneMasDe8Caracteres(){
        Assert.assertFalse(longitudMayorA8.validarContra("Ho45r6u"));
        Assert.assertFalse(longitudMayorA8.validarContra("ch8"));
        Assert.assertFalse(longitudMayorA8.validarContra("qu"));
    }


    // CHECKEAR MAYUSCULAS
    @Test
    public void tieneMayusculas(){
        Assert.assertTrue(tieneUnaMayuscula.validarContra("HolA12345"));
        Assert.assertTrue(tieneUnaMayuscula.validarContra("chAu2138"));
        Assert.assertTrue(tieneUnaMayuscula.validarContra("queTePa7a"));
    }

    @Test
    public void noTieneMayusculas(){
        Assert.assertFalse(tieneUnaMayuscula.validarContra("abcd1234"));
        Assert.assertFalse(tieneUnaMayuscula.validarContra("abdeopsaasd"));
        Assert.assertFalse(tieneUnaMayuscula.validarContra("2359347fghf"));
    }


    // CHECKEAR NUMEROS
    @Test
    public void tieneNumeros(){
        Assert.assertTrue(tieneUnNumero.validarContra("HolA12345"));
        Assert.assertTrue(tieneUnNumero.validarContra("chAu2138"));
        Assert.assertTrue(tieneUnNumero.validarContra("queTePa7a"));
    }

    @Test
    public void noTieneNumeros(){
        Assert.assertFalse(tieneUnNumero.validarContra("abcdABCDE"));
        Assert.assertFalse(tieneUnNumero.validarContra("aslbkpewrd"));
        Assert.assertFalse(tieneUnNumero.validarContra("SDKGJROIROWE"));
    }

    // CHECKEAR ESPACIOS
    @Test
    public void tieneEspacios(){
        Assert.assertFalse(noTieneEspacios.validarContra("hola "));
        Assert.assertFalse(noTieneEspacios.validarContra(" 123"));
        Assert.assertFalse(noTieneEspacios.validarContra("1234 567"));
    }

    @Test
    public void noTieneEspacios(){
        Assert.assertTrue(noTieneEspacios.validarContra("bernardo123"));
        Assert.assertTrue(noTieneEspacios.validarContra("juan4567"));
        Assert.assertTrue(noTieneEspacios.validarContra("contraseniaSinEspacios"));
    }


}
