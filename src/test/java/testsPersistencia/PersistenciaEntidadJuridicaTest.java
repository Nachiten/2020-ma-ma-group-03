package testsPersistencia;

import domain.entities.entidades.EntidadJuridica;
import persistencia.db.EntityManagerHelper;
import domain.entities.tipoEntidadJuridica.Categoria;
import domain.entities.tipoEntidadJuridica.Empresa;
import domain.entities.tipoEntidadJuridica.Sector;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistenciaEntidadJuridicaTest {

    static private Sector construccion;
    static private Sector servicios;
    static private Sector comercio;
    static private Sector industriaYMineria;
    static private Sector agropecuario;

    static private Categoria microConstruccion;
    static private Categoria microServicios;
    static private Categoria microComercio;
    static private Categoria microIndustriaYMineria;
    static private Categoria microAgropecuario;

    static private Categoria pequeniaConstruccion;
    static private Categoria pequeniaServicios;
    static private Categoria pequeniaComercio;
    static private Categoria pequeniaIndustriaYMineria;
    static private Categoria pequeniaAgropecuario;

    static private Categoria medianaTramo1Construccion;
    static private Categoria medianaTramo1Servicios;
    static private Categoria medianaTramo1Comercio;
    static private Categoria medianaTramo1IndustriaYMineria;
    static private Categoria medianaTramo1Agropecuario;

    static private Categoria medianaTramo2Construccion;
    static private Categoria medianaTramo2Servicios;
    static private Categoria medianaTramo2Comercio;
    static private Categoria medianaTramo2IndustriaYMineria;
    static private Categoria medianaTramo2Agropecuario;


    @BeforeClass
    public static void init(){
        construccion = new Sector("Construccion");
        servicios = new Sector("Servicios");
        comercio = new Sector("Comercio");
        industriaYMineria = new Sector("Industria Y Mineria");
        agropecuario = new Sector("Agropecuario");

        microConstruccion = new Categoria("Micro", 19450000, 12);
        pequeniaConstruccion = new Categoria("Pequeña", 115370000, 45);
        medianaTramo1Construccion = new Categoria("MedianaTramo1", 643710000, 200);
        medianaTramo2Construccion = new Categoria("MedianaTramo2", 965460000, 590);

        microServicios = new Categoria("Micro",9900000, 7);
        microComercio = new Categoria("Micro", 36320000, 7);
        microIndustriaYMineria = new Categoria("Micro", 33920000, 15);
        microAgropecuario = new Categoria("Micro", 17260000, 5);


        pequeniaServicios = new Categoria("Pequeña", 59710000, 30);
        pequeniaComercio = new Categoria("Pequeña", 247200000, 35);
        pequeniaIndustriaYMineria = new Categoria("Pequeña", 243290000, 60);
        pequeniaAgropecuario = new Categoria("Pequeña", 71960000, 10);


        medianaTramo1Servicios = new Categoria("MedianaTramo1", 494200000, 165);
        medianaTramo1Comercio = new Categoria("MedianaTramo1", 1821760000, 125);
        medianaTramo1IndustriaYMineria = new Categoria("MedianaTramo1", 1651750000, 235);
        medianaTramo1Agropecuario = new Categoria("MedianaTramo1", 426720000, 50);


        medianaTramo2Servicios = new Categoria("MedianaTramo2", 705790000, 535);
        medianaTramo2Comercio = new Categoria("MedianaTramo2", 2602540000L, 345);
        medianaTramo2IndustriaYMineria = new Categoria("MedianaTramo2", 2540380000L, 655);
        medianaTramo2Agropecuario = new Categoria("MedianaTramo2", 676810000, 215);

        //Asociar sector a categoria
        microConstruccion.setSectorAsociado(construccion);
        pequeniaConstruccion.setSectorAsociado(construccion);
        medianaTramo1Construccion.setSectorAsociado(construccion);
        medianaTramo2Construccion.setSectorAsociado(construccion);

        construccion.addCategorias(microConstruccion, pequeniaConstruccion, medianaTramo1Construccion, medianaTramo2Construccion);

        microServicios.setSectorAsociado(servicios);
        microComercio.setSectorAsociado(comercio);
        microIndustriaYMineria.setSectorAsociado(industriaYMineria);
        microAgropecuario.setSectorAsociado(agropecuario);


        pequeniaServicios.setSectorAsociado(servicios);
        pequeniaComercio.setSectorAsociado(comercio);
        pequeniaIndustriaYMineria.setSectorAsociado(industriaYMineria);
        pequeniaAgropecuario.setSectorAsociado(agropecuario);


        medianaTramo1Servicios.setSectorAsociado(servicios);
        medianaTramo1Comercio.setSectorAsociado(comercio);
        medianaTramo1IndustriaYMineria.setSectorAsociado(industriaYMineria);
        medianaTramo1Agropecuario.setSectorAsociado(agropecuario);


        medianaTramo2Servicios.setSectorAsociado(servicios);
        medianaTramo2Comercio.setSectorAsociado(comercio);
        medianaTramo2IndustriaYMineria.setSectorAsociado(industriaYMineria);
        medianaTramo2Agropecuario.setSectorAsociado(agropecuario);

        //agregar categorias a sector

        servicios.addCategorias(microServicios, pequeniaServicios, medianaTramo1Servicios, medianaTramo2Servicios);
        comercio.addCategorias(microComercio, pequeniaComercio, medianaTramo1Comercio, medianaTramo2Comercio);
        industriaYMineria.addCategorias(microIndustriaYMineria, pequeniaIndustriaYMineria, medianaTramo1IndustriaYMineria, medianaTramo2IndustriaYMineria);
        agropecuario.addCategorias(microAgropecuario, pequeniaAgropecuario, medianaTramo1Agropecuario, medianaTramo2Agropecuario);



    }

    private void persistirUnObjeto(Object unObjeto){

        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(unObjeto);

        EntityManagerHelper.commit();
    }

    @Test
    public void t1_persistirSectorYCategorias(){

        //Persistir Sectores
        persistirUnObjeto(construccion);
        persistirUnObjeto(servicios);
        persistirUnObjeto(comercio);
        persistirUnObjeto(industriaYMineria);
        persistirUnObjeto(agropecuario);

        //Persistir Categorias
        persistirUnObjeto(microConstruccion);
        persistirUnObjeto(microServicios);
        persistirUnObjeto(microComercio);
        persistirUnObjeto(microIndustriaYMineria);
        persistirUnObjeto(microAgropecuario);

        persistirUnObjeto(pequeniaConstruccion);
        persistirUnObjeto(pequeniaServicios);
        persistirUnObjeto(pequeniaComercio);
        persistirUnObjeto(pequeniaIndustriaYMineria);
        persistirUnObjeto(pequeniaAgropecuario);

        persistirUnObjeto(medianaTramo1Construccion);
        persistirUnObjeto(medianaTramo1Servicios);
        persistirUnObjeto(medianaTramo1Comercio);
        persistirUnObjeto(medianaTramo1IndustriaYMineria);
        persistirUnObjeto(medianaTramo1Agropecuario);

        persistirUnObjeto(medianaTramo2Construccion);
        persistirUnObjeto(medianaTramo2Servicios);
        persistirUnObjeto(medianaTramo2Comercio);
        persistirUnObjeto(medianaTramo2IndustriaYMineria);
        persistirUnObjeto(medianaTramo2Agropecuario);
    }

    @Test
    public void t2_persistirUnaEntidadJuridica(){

        //Instancio una entidad juridica y un tipo de entidad juridica
        EntidadJuridica constructoraEntidadJuridica = new EntidadJuridica("ConstructoraBA","ConstructuraBA","Constructora Bs.As S.A.", "20345678", null, "ABC-JFK", null);
        Sector sectorEmpresa = EntityManagerHelper.getEntityManager().find(Sector.class, 1);
        Empresa tipoEntidadJuridicaEmpresa = new Empresa(sectorEmpresa, "Corralon", 115360000,44);
        constructoraEntidadJuridica.setTipoEntidadJuridica(tipoEntidadJuridicaEmpresa);
        persistirUnObjeto(constructoraEntidadJuridica);
    }

    @Test
    public void verificarSectoresPersistidos(){
        Sector sectorConstruccion = EntityManagerHelper.getEntityManager().find(Sector.class, 1);
        Sector sectorServicios = EntityManagerHelper.getEntityManager().find(Sector.class, 2);
        Sector sectorComercio = EntityManagerHelper.getEntityManager().find(Sector.class, 3);
        Sector sectorIndustriaYMineria = EntityManagerHelper.getEntityManager().find(Sector.class, 4);
        Sector sectorAgropecuario = EntityManagerHelper.getEntityManager().find(Sector.class, 5);

        Assert.assertEquals("Construccion", sectorConstruccion.getNombre());
        Assert.assertEquals("Servicios", sectorServicios.getNombre());
        Assert.assertEquals("Comercio", sectorComercio.getNombre());
        Assert.assertEquals("Industria Y Mineria", sectorIndustriaYMineria.getNombre());
        Assert.assertEquals("Agropecuario", sectorAgropecuario.getNombre());
    }

}
