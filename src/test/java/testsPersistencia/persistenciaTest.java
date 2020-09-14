package testsPersistencia;

import Persistencia.db.EntityManagerHelper;
import Usuarios.ContraAnterior;
import Usuarios.TipoUsuario;
import Usuarios.Usuario;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class persistenciaTest {

    @Test
    public void persistirUsuario(){
        Usuario miUsuario1 = new Usuario(TipoUsuario.ESTANDAR, "Nachiten", "zxcpkazxm123ABC");

        LocalDate unaFecha1 = LocalDate.of(2015, Month.JULY, 29);
        LocalDate unaFecha2 = LocalDate.of(2017, Month.FEBRUARY, 5);

        miUsuario1.setTiempoUltimaContrasenia(unaFecha1);

        ContraAnterior contraAnterior1 = new ContraAnterior(miUsuario1, "jorge123", unaFecha1);
        ContraAnterior contraAnterior2 = new ContraAnterior(miUsuario1, "fernando456", unaFecha2);

        miUsuario1.agregarContraAnterior(contraAnterior1);
        miUsuario1.agregarContraAnterior(contraAnterior2);

        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(miUsuario1);

        EntityManagerHelper.commit();
    }
}
