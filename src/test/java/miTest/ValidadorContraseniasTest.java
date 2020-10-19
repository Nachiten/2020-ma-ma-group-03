package miTest;

import domain.entities.usuarios.TipoUsuario;
import domain.entities.usuarios.Usuario;
import validadoresContrasenia.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ValidadorContraseniasTest {

    Usuario miUsuario1 = new Usuario(TipoUsuario.ESTANDAR, "Nachiten", "zxcpkazxm123ABC");
    Usuario miUsuario2 = new Usuario(TipoUsuario.ESTANDAR, "Noe", "abcde123ABC");
    Usuario miUsuario3 = new Usuario(TipoUsuario.ADMIN, "Carlos", "aaaaa123ABC");
    Usuario miUsuario4 = new Usuario(TipoUsuario.ADMIN, "Maxi", "cosas123ABC");

    @Test
    public void probarContrasenias(){
        Assert.assertSame(miUsuario1.getContrasenia(),"zxcpkazxm123ABC");
        Assert.assertSame(miUsuario2.getContrasenia(),"abcde123ABC");
        Assert.assertSame(miUsuario3.getContrasenia(),"aaaaa123ABC");
        Assert.assertSame(miUsuario4.getContrasenia(),"cosas123ABC");
    }


    Validador longitudMayorA8 = new ValidadorLongitud(8);
    Validador tieneUnaMayuscula = new ValidadorMayusculas(1);
    Validador tieneUnNumero = new ValidadorNumeros(1);
    Validador noTieneEspacios = new ValidadorEspacios();
    Validador noEstaEnPeoresContra = new ValidadorPeoresContrasenias("./archivos/topPeoresContrasenias.txt");

    ValidadorCredenciales miValidador = new ValidadorCredenciales(Arrays.asList(longitudMayorA8, tieneUnaMayuscula, tieneUnNumero, noTieneEspacios, noEstaEnPeoresContra));

    // CHECKEAR CONTRASEÑAS
    @Test
    public void contraseniasNOSeguras(){
        Assert.assertFalse(miValidador.esSegura("holaaaaa")); // No tiene ni mayus ni numero
        Assert.assertFalse(miValidador.esSegura("Hola123")); // No son 8 caracteres
        Assert.assertFalse(miValidador.esSegura("HolaAAAA")); // Falta numero
        Assert.assertFalse(miValidador.esSegura("comoestas123")); // Falta mayus
        Assert.assertFalse(miValidador.esSegura("123456789")); //Peor contraseña
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
        Assert.assertTrue(longitudMayorA8.validarContrasenia("HolA12345"));
        Assert.assertTrue(longitudMayorA8.validarContrasenia("chAu2138"));
        Assert.assertTrue(longitudMayorA8.validarContrasenia("queTePa7a"));
    }

    @Test
    public void noTieneMasDe8Caracteres(){
        Assert.assertFalse(longitudMayorA8.validarContrasenia("Ho45r6u"));
        Assert.assertFalse(longitudMayorA8.validarContrasenia("ch8"));
        Assert.assertFalse(longitudMayorA8.validarContrasenia("qu"));
    }


    // CHECKEAR MAYUSCULAS
    @Test
    public void tieneMayusculas(){
        Assert.assertTrue(tieneUnaMayuscula.validarContrasenia("HolA12345"));
        Assert.assertTrue(tieneUnaMayuscula.validarContrasenia("chAu2138"));
        Assert.assertTrue(tieneUnaMayuscula.validarContrasenia("queTePa7a"));
    }

    @Test
    public void noTieneMayusculas(){
        Assert.assertFalse(tieneUnaMayuscula.validarContrasenia("abcd1234"));
        Assert.assertFalse(tieneUnaMayuscula.validarContrasenia("abdeopsaasd"));
        Assert.assertFalse(tieneUnaMayuscula.validarContrasenia("2359347fghf"));
    }


    // CHECKEAR NUMEROS
    @Test
    public void tieneNumeros(){
        Assert.assertTrue(tieneUnNumero.validarContrasenia("HolA12345"));
        Assert.assertTrue(tieneUnNumero.validarContrasenia("chAu2138"));
        Assert.assertTrue(tieneUnNumero.validarContrasenia("queTePa7a"));
    }

    @Test
    public void noTieneNumeros(){
        Assert.assertFalse(tieneUnNumero.validarContrasenia("abcdABCDE"));
        Assert.assertFalse(tieneUnNumero.validarContrasenia("aslbkpewrd"));
        Assert.assertFalse(tieneUnNumero.validarContrasenia("SDKGJROIROWE"));
    }

    // CHECKEAR ESPACIOS
    @Test
    public void tieneEspacios(){
        Assert.assertFalse(noTieneEspacios.validarContrasenia("hola "));
        Assert.assertFalse(noTieneEspacios.validarContrasenia(" 123"));
        Assert.assertFalse(noTieneEspacios.validarContrasenia("1234 567"));
    }

    @Test
    public void noTieneEspacios(){
        Assert.assertTrue(noTieneEspacios.validarContrasenia("bernardo123"));
        Assert.assertTrue(noTieneEspacios.validarContrasenia("juan4567"));
        Assert.assertTrue(noTieneEspacios.validarContrasenia("contraseniaSinEspacios"));
    }

    //CHEQUEA EN LA LISTA DE PEORES CONTRASEÑAS
    @Test
    public void estaEnPeoresContra(){
        Assert.assertFalse(noEstaEnPeoresContra.validarContrasenia("password"));
        Assert.assertFalse(noEstaEnPeoresContra.validarContrasenia("123456789"));
        Assert.assertFalse(noEstaEnPeoresContra.validarContrasenia("sunshine!"));
    }

    @Test
    public void noEstaEnPeoresContra(){
        Assert.assertTrue(noEstaEnPeoresContra.validarContrasenia("SistemasJava"));
        Assert.assertTrue(noEstaEnPeoresContra.validarContrasenia("E123dfgd89"));
        Assert.assertTrue(noEstaEnPeoresContra.validarContrasenia("disenio123"));
    }
}