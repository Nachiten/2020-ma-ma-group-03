package testsPersistencia;

import Entidades.EntidadJuridica;
import Persistencia.db.EntityManagerHelper;
import TipoEntidadJuridica.Categoria;
import TipoEntidadJuridica.Empresa;
import TipoEntidadJuridica.Sector;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersistenciaEntidadJuridicaTest {
    private void persistirUnObjeto(Object unObjeto){
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(unObjeto);

        EntityManagerHelper.commit();
    }

    static Sector construccion;
    static Sector servicios;
    static Sector comercio;
    static Sector industriaYMineria;
    static Sector agropecuario;

    static Categoria microConstruccion;
    static Categoria pequeniaServicios;
    static Categoria medianaTramo1IndustriaMineria;
    static Categoria medianaTramo2Agropecuario;

    @BeforeClass
    public static void init(){
        construccion = new Sector("Construccion");
        servicios = new Sector("Servicios");
        comercio = new Sector("Comercio");
        industriaYMineria = new Sector("IndustriaYMineria");
        agropecuario = new Sector("Agropecuario");

        microConstruccion = new Categoria("Micro", 12, 19450000);
        pequeniaServicios = new Categoria("Pequeña", 30, 59710000);
        medianaTramo1IndustriaMineria = new Categoria("MedianaTramo1", 235, 1651750000);
        medianaTramo2Agropecuario = new Categoria("MedianaTramo2", 215, 676810000);

        construccion.addCategorias(microConstruccion, pequeniaServicios, medianaTramo1IndustriaMineria, medianaTramo2Agropecuario);
    }

    @Test
    public void persistirUnaEntidadJuridica(){

        EntidadJuridica miEntidadJuridica = new EntidadJuridica("Nombre S.A.", "20345678", null, "ABC-JFK");

        Empresa miEmpresa = new Empresa(construccion, 200000,500);

        miEntidadJuridica.setTipoEntidadJuridica(miEmpresa);

        persistirUnObjeto(miEntidadJuridica);
    }

}
