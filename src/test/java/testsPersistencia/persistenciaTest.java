package testsPersistencia;

import Persistencia.db.EntityManagerHelper;
import Usuarios.ContraAnterior;
import Usuarios.Mensaje;
import Usuarios.TipoUsuario;
import Usuarios.Usuario;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class persistenciaTest {

    private Usuario generarUsuarioConContraAnterior(String nombreUsuario, String contrasenia){
        Usuario miUsuario1 = new Usuario(TipoUsuario.ESTANDAR, nombreUsuario, contrasenia);

        LocalDate unaFecha1 = LocalDate.of(2015, Month.JULY, 29);
        LocalDate unaFecha2 = LocalDate.of(2017, Month.FEBRUARY, 5);

        miUsuario1.setTiempoUltimaContrasenia(unaFecha1);

        ContraAnterior contraAnterior1 = new ContraAnterior(miUsuario1, "jorge123", unaFecha1);
        ContraAnterior contraAnterior2 = new ContraAnterior(miUsuario1, "fernando456", unaFecha2);

        miUsuario1.agregarContraAnterior(contraAnterior1);
        miUsuario1.agregarContraAnterior(contraAnterior2);

        return miUsuario1;
    }

    private void persistirUnObjeto(Object unObjeto){
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(unObjeto);

        EntityManagerHelper.commit();
    }

    @Test
    public void persistirUsuario(){
        Usuario miUsuario = generarUsuarioConContraAnterior("Nachiten", "hola1234ABC");

        persistirUnObjeto(miUsuario);
    }

    @Test
    public void persistirUsuarioConBandejaDeMensajes(){
        Usuario miUsuario = generarUsuarioConContraAnterior("Carlos", "jorge123BCA");

        miUsuario.publicarMensajeEnBandejaDeMensajes("Un resultado",true );
        miUsuario.publicarMensajeEnBandejaDeMensajes("Un resultado 2",false );
        miUsuario.publicarMensajeEnBandejaDeMensajes("Un resultado 3",true );

        /*
        *  Si le digo directamente que persista un mensaje si lo hace bien
        *  (entiendo que no lo hace solo ya que no hay una referencia directa dede usuario hacia mensaje
        *  entonces no entiende que tiene que persistirlo)
        */
        //Mensaje unMensaje = new Mensaje(true, "jorge", miUsuario);

        persistirUnObjeto(miUsuario);
       //persistirUnObjeto(unMensaje);
    }
}
