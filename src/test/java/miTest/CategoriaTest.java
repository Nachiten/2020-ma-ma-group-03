package miTest;

import CriterioSeleccionProveedor.CriterioProveedorMenorValor;
import Entidades.Afip;
import Entidades.EntidadJuridica;
import Operaciones.*;
import TipoEntidadJuridica.Categoria;
import TipoEntidadJuridica.Empresa;
import TipoEntidadJuridica.Sector;
import ValidadorTransparencia.*;
import Vendedor.Proveedor;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CategoriaTest {

    //Categorías
    private final Categoria micro = new Categoria("Micro",8500000,7);
    private final Categoria pequenia = new Categoria("Pequenia",50950000,30);
    private final Categoria medianaT1 = new Categoria("MedianaT1",425170000,165);
    private final Categoria medianaT2 = new Categoria("MedianaT2",607210000,535);

    //Agrego categorías a sector
    private final List<Categoria> categorias = new ArrayList<>(Arrays.asList(micro, pequenia, medianaT1, medianaT2));

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

    public void agregarCategorias(Sector sector, List<Categoria> categorias) {
        for(Categoria unaCategoria: categorias){
            sector.addCategorias(unaCategoria);
        }
    }

    @Test
    public void clasificacionConMicro() {
        agregarCategorias(servicios1, categorias);
        Assert.assertSame(Afip.clasificacion(empresaA), micro);
    }

    @Test
    public void clasificacionConPequenia() {
        agregarCategorias(servicios2, categorias);
        Assert.assertSame(Afip.clasificacion(empresaB), pequenia);
    }

    @Test
    public void clasificacionConMedianaT1() {
        agregarCategorias(servicios3, categorias);
        Assert.assertSame(Afip.clasificacion(empresaC), medianaT1);
    }

    @Test
    public void clasificacionConMedianaT2() {
        agregarCategorias(servicios4, categorias);
        Assert.assertSame(Afip.clasificacion(empresaD), medianaT2);
    }

}


