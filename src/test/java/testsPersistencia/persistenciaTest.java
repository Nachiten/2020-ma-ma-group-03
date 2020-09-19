package testsPersistencia;

import Operaciones.*;
import Persistencia.db.EntityManagerHelper;
import Usuarios.*;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class persistenciaTest {

    OperacionDeEgreso operacionEgresoConstruccion;
    OperacionDeEgreso operacionDeEgresoRopaA;

    private void generarOperacionesDeEgreso(){
        TipoMedioDePago tarjetaDeCredito = new TipoMedioDePago("tarjetaDeCredito");
        MedioDePago medioDePagoTarjetaDeCredito = new MedioDePago(tarjetaDeCredito, 9468753);

        TipoDocumentoComercial cheque = new TipoDocumentoComercial("cheque");
        DocumentoComercial documentoCheque = new DocumentoComercial(cheque, 4534784);

        TipoDocumentoComercial recibo = new TipoDocumentoComercial("recibo");
        DocumentoComercial documentoRecibo = new DocumentoComercial(recibo, 43);

        //InstanciasDeConstruccion
        Item itemPresupuestoConstruccion1 = new Item("Ladrillos 1 millar", 40000);
        Item itemPresupuestoConstruccion2 = new Item("Cemento x 25KG", 400);
        Item itemPresupuestoConstruccion3 = new Item("Alambre x 5KG", 200);
        Item itemPresupuestoConstruccion4 = new Item("Varilla de hierro", 180);
        Item itemPresupuestoConstruccion5 = new Item("Arena x 10KG", 1650);
        List<Item> itemsPresupuestoConstruccion = new ArrayList<>(Arrays.asList(itemPresupuestoConstruccion1, itemPresupuestoConstruccion2, itemPresupuestoConstruccion3, itemPresupuestoConstruccion4, itemPresupuestoConstruccion5));

        //Instancias de RopaA
        Item itemPresupuestoRopaA1 = new Item("Remera Talle L", 1000);
        Item itemPresupuestoRopaA2 = new Item("Pantalon Talle 42", 1550);
        Item itemPresupuestoRopaA3 = new Item("Remera Talle S", 600);
        Item itemPresupuestoRopaA4 = new Item("Remera Talle M", 800);
        Item itemPresupuestoRopaA5 = new Item("Pantalon Talle 44", 1650);
        List<Item> itemsPresupuestoRopaA = new ArrayList<>(Arrays.asList(itemPresupuestoRopaA1, itemPresupuestoRopaA2, itemPresupuestoRopaA3, itemPresupuestoRopaA4, itemPresupuestoRopaA5));

        List<Presupuesto> presupuestosRopaA = new ArrayList<>();
        List<Presupuesto> presupuestosConstruccion = new ArrayList<>();

        //Egreso Ropa A
        operacionDeEgresoRopaA = new OperacionDeEgreso(new Date(),5600, medioDePagoTarjetaDeCredito, itemsPresupuestoRopaA);
        Presupuesto presupuestoRopaA = new Presupuesto(5600, itemsPresupuestoRopaA, operacionDeEgresoRopaA);

        operacionEgresoConstruccion = new OperacionDeEgreso(new Date(),42430, medioDePagoTarjetaDeCredito, itemsPresupuestoConstruccion);
        Presupuesto presupuestoConstruccion = new Presupuesto(42430, itemsPresupuestoConstruccion, operacionEgresoConstruccion);

        operacionDeEgresoRopaA.adjuntarDocumentoComercial(documentoRecibo);
    }

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

        miUsuario.publicarMensajeEnBandejaDeMensajes("Un resultado 1",true );
        miUsuario.publicarMensajeEnBandejaDeMensajes("Un resultado 2",false );
        miUsuario.publicarMensajeEnBandejaDeMensajes("Un resultado 3",true );

        for (Mensaje unMensaje: miUsuario.getBandejaDeMensajes()
             ) {
                System.out.println(unMensaje.getContenido() + " -- " + unMensaje.getUsuarioAsociado());
        }
        /*
        *  Si le digo directamente que persista un mensaje si lo hace bien
        *  (entiendo que no lo hace solo ya que no hay una referencia directa dede usuario hacia mensaje
        *  entonces no entiende que tiene que persistirlo)
        */
        //Mensaje unMensaje = new Mensaje(true, "jorge", miUsuario);

        persistirUnObjeto(miUsuario);
       //persistirUnObjeto(unMensaje);
    }

    @Test
    public void persistirOperacionDeEgreso(){
        generarOperacionesDeEgreso();

        persistirUnObjeto(operacionDeEgresoRopaA);
    }
}
