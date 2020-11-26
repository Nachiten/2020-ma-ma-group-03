package testsPersistencia;

import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.*;
import persistencia.db.EntityManagerHelper;
import domain.entities.usuarios.*;
import domain.entities.vendedor.Proveedor;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersistenciaOperacionesTest {

    ////////////////////////////////////////////////////////////////////////////////////////////
    // ASEGURESE DE CORRER PRIMERO EL TEST PersistenciaTipoTest y PersistenciaEntidadJuridica //
    ////////////////////////////////////////////////////////////////////////////////////////////

    static private OperacionDeEgreso operacionEgresoConstruccion;
    static private OperacionDeEgreso operacionDeEgresoRopaA;
    static private OperacionDeIngreso operacionIngreso;

    static private Usuario usuarioA;
    static private Usuario usuarioB;
    //agregado
    static private Usuario usuarioC;
    static private Usuario usuarioAdmin;

    static private TipoMedioDePago tarjetaDeCredito;
    static private MedioDePago medioDePagoTarjetaDeCredito;

    static private TipoDocumentoComercial cheque;
    static private DocumentoComercial documentoCheque;

    static private TipoDocumentoComercial recibo;
    static private DocumentoComercial documentoRecibo;

    static private Item itemPresupuestoConstruccion1;
    static private Item itemPresupuestoConstruccion2;
    static private Item itemPresupuestoConstruccion3;
    static private Item itemPresupuestoConstruccion4;
    static private Item itemPresupuestoConstruccion5;

    static private List<Item> itemsPresupuestoConstruccion;

    static private Item itemPresupuestoRopaA1;
    static private Item itemPresupuestoRopaA2;
    static private Item itemPresupuestoRopaA3;
    static private Item itemPresupuestoRopaA4;
    static private Item itemPresupuestoRopaA5;

    static private List<Item> itemsPresupuestoRopaA;

    static private Presupuesto presupuestoConstruccion;
    static private Presupuesto presupuestoRopaA;

    static private EntidadJuridica entidadJuridica;


    @BeforeClass
    public static void init() {

        //Instancia de usuario
        usuarioA = new Usuario(TipoUsuario.ESTANDAR,"Nachiten","hola1234ABC", "Ignacio", "Baptista");
        usuarioB = new Usuario(TipoUsuario.ESTANDAR,"Carlos","asdfg", "Carlos", "Villegas");
        usuarioC = new Usuario(TipoUsuario.ESTANDAR,"Edu","kocina77","Eduardo","Lopez");
        usuarioAdmin = new Usuario(TipoUsuario.ADMIN,"Admin", "admin", "Admin", "Root");

        generarUsuarioAConContraAnterior();

        //Poner en desabilitado a usuario C
        usuarioC.setEstoyHabilitado(false);

        //Mensaje en usuarioB
        usuarioB.publicarMensajeEnBandejaDeMensajes("Un resultado 1",true );
        usuarioB.publicarMensajeEnBandejaDeMensajes("Un resultado 2",false );
        usuarioB.publicarMensajeEnBandejaDeMensajes("Un resultado 3",true );

        EntidadJuridica entidadJuridicaUsuarioB = EntityManagerHelper.getEntityManager().find(EntidadJuridica.class, 1);

        usuarioB.setEntidadJuridica(entidadJuridicaUsuarioB);
        usuarioA.setEntidadJuridica(entidadJuridicaUsuarioB);
        usuarioC.setEntidadJuridica(entidadJuridicaUsuarioB);

        //Instancias de MedioDePago
        tarjetaDeCredito = EntityManagerHelper.getEntityManager().find(TipoMedioDePago.class, 1);
        medioDePagoTarjetaDeCredito = new MedioDePago(tarjetaDeCredito,"9468753");

        //Instancias de DocumentoComercial
        cheque = EntityManagerHelper.getEntityManager().find(TipoDocumentoComercial.class, 5);
        documentoCheque = new DocumentoComercial(cheque,4534784);

        recibo = EntityManagerHelper.getEntityManager().find(TipoDocumentoComercial.class, 4);
        documentoRecibo = new DocumentoComercial(recibo, 43);

        //Instancias items Construccion
        itemPresupuestoConstruccion1 = new Item("Ladrillos 1 millar",40000);
        itemPresupuestoConstruccion2 = new Item("Cemento x 25KG", 400);
        itemPresupuestoConstruccion3 = new Item("Alambre x 5KG", 200);
        itemPresupuestoConstruccion4 = new Item("Varilla de hierro", 180);
        itemPresupuestoConstruccion5 = new Item("Arena x 10KG", 1650);
        itemsPresupuestoConstruccion = new ArrayList<>(Arrays.asList(itemPresupuestoConstruccion1, itemPresupuestoConstruccion2,
                itemPresupuestoConstruccion3, itemPresupuestoConstruccion4, itemPresupuestoConstruccion5));

        //Instancias de items RopaA
        itemPresupuestoRopaA1 = new Item("Remera Talle L", 1000);
        itemPresupuestoRopaA2 = new Item("Pantalon Talle 42", 1550);
        itemPresupuestoRopaA3 = new Item("Remera Talle S", 600);
        itemPresupuestoRopaA4 = new Item("Remera Talle M", 800);
        itemPresupuestoRopaA5 = new Item("Pantalon Talle 44", 1650);
        itemsPresupuestoRopaA = new ArrayList<>(Arrays.asList(itemPresupuestoRopaA1, itemPresupuestoRopaA2, itemPresupuestoRopaA3,
                itemPresupuestoRopaA4, itemPresupuestoRopaA5));

        //Instancia de domain.entities.operaciones de egreso
        operacionEgresoConstruccion = new OperacionDeEgreso(LocalDate.now(), 42430, medioDePagoTarjetaDeCredito, itemsPresupuestoConstruccion);
        operacionEgresoConstruccion.setUsuario(usuarioB);
        operacionEgresoConstruccion.setCantidadPresupuestosRequerida(1);
        operacionEgresoConstruccion.setDocumentoComercial(documentoCheque);

        operacionDeEgresoRopaA = new OperacionDeEgreso(LocalDate.now(), 5600, medioDePagoTarjetaDeCredito, itemsPresupuestoRopaA);
        operacionDeEgresoRopaA.setUsuario(usuarioA);
        operacionDeEgresoRopaA.setCantidadPresupuestosRequerida(1);
        operacionDeEgresoRopaA.setDocumentoComercial(documentoRecibo);

        //Instancia de presupuestos
        presupuestoRopaA = new Presupuesto(5600, itemsPresupuestoRopaA, operacionDeEgresoRopaA);

        presupuestoConstruccion = new Presupuesto(42430, itemsPresupuestoConstruccion, operacionEgresoConstruccion);

        //Cargar presupuesto a las domain.entities.operaciones de egreso
        operacionDeEgresoRopaA.agregarPresupuesto(presupuestoRopaA);

        operacionEgresoConstruccion.agregarPresupuesto(presupuestoConstruccion);

        //Asociar entidad juridica
        entidadJuridica = EntityManagerHelper.getEntityManager().find(EntidadJuridica.class, 1);
        operacionDeEgresoRopaA.setEntidadJuridicaAsociada(entidadJuridica);

        // Agrego revisores
        operacionDeEgresoRopaA.agregarRevisor(usuarioA);
        operacionDeEgresoRopaA.agregarRevisor(usuarioB);

        // Seteo proveedor
        Proveedor miProveedor = new Proveedor("Roberto", "Fernandez", "42374333", null, "RopaLinda S.A.");
        operacionDeEgresoRopaA.setProveedorAsociado(miProveedor);

        // Creo y asocio operacion de ingreso
        operacionIngreso = new OperacionDeIngreso("Venta de algo", 5000, LocalDate.now());
        operacionDeEgresoRopaA.setOperacionDeIngresoId(operacionIngreso);

        System.out.println("Instancie todo");

    }


    private static void generarUsuarioAConContraAnterior(){
        LocalDate unaFecha1 = LocalDate.of(2015, Month.JULY, 29);
        LocalDate unaFecha2 = LocalDate.of(2017, Month.FEBRUARY, 5);

        usuarioA.setTiempoUltimaContrasenia(unaFecha1);

        ContraAnterior contraAnterior1 = new ContraAnterior(usuarioA, "jorge123", unaFecha1);
        ContraAnterior contraAnterior2 = new ContraAnterior(usuarioA, "fernando456", unaFecha2);

        usuarioA.agregarContraAnterior(contraAnterior1);
        usuarioA.agregarContraAnterior(contraAnterior2);
    }

    private void persistirUnObjeto(Object unObjeto){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unObjeto);
        EntityManagerHelper.commit();
    }


    @Test
    public void t1_persistirUsuarioB(){
        persistirUnObjeto(usuarioB);
    }

    @Test
    public void t2_persistirOperacionDeEgresoConUsuarioA(){
        persistirUnObjeto(operacionDeEgresoRopaA);
        persistirUnObjeto(operacionIngreso);
    }


    @Test
    public void t3_persistirUsuarioAdmin(){ persistirUnObjeto(usuarioAdmin);}

    @Test
    public void t4_persistirProveedores(){
        Proveedor miProveedor1 = new Proveedor("Ramirez", "Carla", "41374383", null, "Contruccion SRL");
        persistirUnObjeto(miProveedor1);

        Proveedor miProveedor2 = new Proveedor("Baptista", "Fernando", "42954333", null, "Cosas SA");
        persistirUnObjeto(miProveedor2);

        Proveedor miProveedor3 = new Proveedor("Martinez", "Julian", "36374333", null, "Alimentos SA");
        persistirUnObjeto(miProveedor3);
    }
    @Test
    public void t5_persistirUsuarioC(){persistirUnObjeto(usuarioC);}

    @Test
    public void verificarUsuarioAPersistido(){

        Usuario unUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 2);
        TipoUsuario tipo = TipoUsuario.ESTANDAR;

        Assert.assertEquals("Nachiten", unUsuario.getNombreUsuario());
        Assert.assertEquals(tipo,unUsuario.getTipo());

    }

    @Test
    public void verificarUsuarioBPersistido(){

        Usuario unUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);
        TipoUsuario tipo = TipoUsuario.ESTANDAR;

        Assert.assertEquals("Carlos", unUsuario.getNombreUsuario());
        Assert.assertEquals(tipo,unUsuario.getTipo());

    }


    @Test
    public void verificarUsuarioAdminPersistido(){

        Usuario unUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 3);
        TipoUsuario tipo = TipoUsuario.ADMIN;

        Assert.assertEquals("Admin", unUsuario.getNombreUsuario());
        Assert.assertEquals(tipo,unUsuario.getTipo());
    }
    @Test
    public void verificarUsuarioCPersistido(){

        Usuario unUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 4);
        TipoUsuario tipo = TipoUsuario.ESTANDAR;

        Assert.assertEquals("Edu", unUsuario.getNombreUsuario());
        Assert.assertEquals(tipo,unUsuario.getTipo());

    }

    @Test
    public void verificarOperacionDeEgresoRopaAPersistido(){

        OperacionDeEgreso unaoperacionDeEgreso = EntityManagerHelper.getEntityManager().find(OperacionDeEgreso.class, 1);

        Assert.assertEquals(1, unaoperacionDeEgreso.getCantidadPresupuestosRequerida());
        Assert.assertEquals(5600, unaoperacionDeEgreso.getMontoTotal(), 0.0);
    }
}
