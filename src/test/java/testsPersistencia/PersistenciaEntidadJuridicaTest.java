package testsPersistencia;

import ApiMercadoLibre.DireccionPostal;
import Entidades.EntidadJuridica;
import Operaciones.OperacionDeEgreso;
import Persistencia.db.EntityManagerHelper;
import TipoEntidadJuridica.Empresa;
import TipoEntidadJuridica.Sector;
import org.junit.Test;

public class PersistenciaEntidadJuridicaTest {
    private void persistirUnObjeto(Object unObjeto){
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(unObjeto);

        EntityManagerHelper.commit();
    }

    @Test
    public void persistirUnaEntidadJuridica(){

        EntidadJuridica miEntidadJuridica = new EntidadJuridica("Nombre S.A.", "20345678", null, "ABC-JFK");

        Empresa miEmpresa = new Empresa(null, 200000,500);

        miEntidadJuridica.setTipoEntidadJuridica(miEmpresa);

        persistirUnObjeto(miEntidadJuridica);
    }

}
