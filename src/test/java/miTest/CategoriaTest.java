package miTest;

import Entidades.Afip;
import TipoEntidadJuridica.Categoria;
import TipoEntidadJuridica.Empresa;
import TipoEntidadJuridica.Sector;
import org.junit.Assert;
import org.junit.Test;

public class CategoriaTest {

    //Categorías
    private final Categoria micro = new Categoria("Micro",8500000,7);
    private final Categoria pequenia = new Categoria("Pequenia",50950000,30);
    private final Categoria medianaT1 = new Categoria("MedianaT1",425170000,165);
    private final Categoria medianaT2 = new Categoria("MedianaT2",607210000,535);

    //Sector
    private final Sector servicios1 = new Sector();
    private final Sector servicios2 = new Sector();
    private final Sector servicios3 = new Sector();
    private final Sector servicios4 = new Sector();

    //Empresa
    private final Empresa empresaA = new Empresa(servicios1, 8000000,5);
    private final Empresa empresaB = new Empresa(servicios2, 40950000,25);
    private final Empresa empresaC = new Empresa(servicios3, 425170000,15);
    private final Empresa empresaD = new Empresa(servicios4,425161000,534);

    @Test
    public void clasificacionConMicro() {
        servicios1.addCategorias(micro, pequenia, medianaT1);
        servicios1.addCategorias(medianaT2);
        Assert.assertSame(Afip.clasificacion(empresaA), micro);
    }

    @Test
    public void clasificacionConPequenia() {
        servicios2.addCategorias(micro, pequenia, medianaT1, medianaT2);
        Assert.assertSame(Afip.clasificacion(empresaB), pequenia);
    }

    @Test
    public void clasificacionConMedianaT1() {
        servicios3.addCategorias(micro, pequenia, medianaT1, medianaT2);
        Assert.assertSame(Afip.clasificacion(empresaC), medianaT1);
    }

    @Test
    public void clasificacionConMedianaT2() {
        servicios4.addCategorias(micro, pequenia, medianaT1, medianaT2);
        Assert.assertSame(Afip.clasificacion(empresaD), medianaT2);
    }
}


