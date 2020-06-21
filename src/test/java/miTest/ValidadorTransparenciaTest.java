package miTest;

import CriterioSeleccionProveedor.CriterioProveedorMenorValor;
import Entidades.EntidadJuridica;
import Operaciones.*;
import ValidadorTransparencia.*;
import Vendedor.Proveedor;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ValidadorTransparenciaTest {

    //Items Calzado
    private final Item itemPresupuestoCalzado1 = new Item("Botas mujer", 3500);
    private final Item itemPresupuestoCalzado2 = new Item("Botas hombre", 3000);
    private final Item itemPresupuestoCalzado3 = new Item("Zapatos de tacon", 4500);
    private final Item itemPresupuestoCalzado4 = new Item("Sandalias romanas", 2500);
    private final Item itemPresupuestoCalzado5 = new Item("Zapatillas tenis", 3700);
    private final List<Item> itemsPresupuestoCalzado = new ArrayList<>(Arrays.asList(itemPresupuestoCalzado1, itemPresupuestoCalzado2, itemPresupuestoCalzado3, itemPresupuestoCalzado4, itemPresupuestoCalzado5));

    //Items Oficina
    private final Item itemPresupuestoOficina1 = new Item("Escritorio", 15000);
    private final Item itemPresupuestoOficina2 = new Item("Silla", 6000);
    private final Item itemPresupuestoOficina3 = new Item("Papelera", 3000);
    private final Item itemPresupuestoOficina4 = new Item("Porta documentos", 15000);
    private final Item itemPresupuestoOficina5 = new Item("Lapiceros", 500);
    private final List<Item> itemsPresupuestoOficina = new ArrayList<>(Arrays.asList(itemPresupuestoOficina1, itemPresupuestoOficina2, itemPresupuestoOficina3, itemPresupuestoOficina4, itemPresupuestoOficina5));

    //Documentos comerciales
    private final DocumentoComercial recibo = new DocumentoComercial("Recibo", 8678478);
    private final DocumentoComercial cheque = new DocumentoComercial("Cheque", 4534784);
    private final DocumentoComercial remito = new DocumentoComercial("Remito", 4547888);
    private final DocumentoComercial facturaTipoB = new DocumentoComercial("Factura Tipo B", 4547889);


    //Proveedores
    private final Proveedor indumentariaDeportivaBsAs = new Proveedor("Indumentaria deportiva Bs As",58462146,"4563");
    private final Proveedor proveedor2 = new Proveedor("Constructora Comaf",12762146,"876");


    //Medios de Pago

    private final MedioDePago tarjetaDeCredito = new MedioDePago("Tarjeta de credito", 9468753);

    //Items Ropa A

    private final Item itemPresupuestoRopaA1 = new Item("Remera Talle L", 1000);
    private final Item itemPresupuestoRopaA2 = new Item("Pantalon Talle 42", 1550);
    private final Item itemPresupuestoRopaA3 = new Item("Remera Talle S", 600);
    private final Item itemPresupuestoRopaA4 = new Item("Remera Talle M", 800);
    private final Item itemPresupuestoRopaA5 = new Item("Pantalon Talle 44", 1650);
    private final List<Item> itemsPresupuestoRopaA = new ArrayList<>(Arrays.asList(itemPresupuestoRopaA1, itemPresupuestoRopaA2, itemPresupuestoRopaA3, itemPresupuestoRopaA4, itemPresupuestoRopaA5));

    //Egreso Ropa A

    private final DocumentoComercial documentoComercialRopaA = recibo;
    private final Proveedor proveedorRopaA = indumentariaDeportivaBsAs;
    private final MedioDePago medioDePagoRopaA = tarjetaDeCredito;
    private final OperacionDeEgreso operacionDeEgresoRopaA = new OperacionDeEgreso(proveedorRopaA,new Date(),5600, medioDePagoRopaA, documentoComercialRopaA,itemsPresupuestoRopaA);
    private final Presupuesto presupuestoRopaA = new Presupuesto(5600, itemsPresupuestoRopaA, documentoComercialRopaA);


    //Items de Ropa B

    private final Item itemPresupuestoRopaB1 = new Item("Camisa Talle L", 1100);
    private final Item itemPresupuestoRopaB2 = new Item("Pantalon Talle 42", 1550);
    private final Item itemPresupuestoRopaB3 = new Item("Camisa Talle S", 650);
    private final Item itemPresupuestoRopaB4 = new Item("Camisa Talle M", 850);
    private final Item itemPresupuestoRopaB5 = new Item("Pantalon Talle 44", 1650);
    private final List<Item> itemsPresupuestoRopaB = new ArrayList<>(Arrays.asList(itemPresupuestoRopaB1, itemPresupuestoRopaB2, itemPresupuestoRopaB3, itemPresupuestoRopaB4, itemPresupuestoRopaB5));

    //Presupuesto Ropa B

    private final DocumentoComercial documentoComercialRopaB = cheque;
    private final Presupuesto presupuestoRobaB = new Presupuesto(5800, itemsPresupuestoRopaB, documentoComercialRopaB);


    //Items de Construccion

    private final Item itemPresupuestoConstruccion1 = new Item("Ladrillos 1 millar", 40000);
    private final Item itemPresupuestoConstruccion2 = new Item("Cemento x 25KG", 400);
    private final Item itemPresupuestoConstruccion3 = new Item("Alambre x 5KG", 200);
    private final Item itemPresupuestoConstruccion4 = new Item("Varilla de hierro", 180);
    private final Item itemPresupuestoConstruccion5 = new Item("Arena x 10KG", 1650);
    private final List<Item> itemsPresupuestoConstruccion = new ArrayList<>(Arrays.asList(itemPresupuestoConstruccion1, itemPresupuestoConstruccion2, itemPresupuestoConstruccion3, itemPresupuestoConstruccion4, itemPresupuestoConstruccion5));

    //Egreso construccion

    private final Proveedor proveedorconstruccion = proveedor2;
    private final MedioDePago medioDePagoconstruccion = tarjetaDeCredito;
    private final OperacionDeEgreso operacionEgresoConstruccion = new OperacionDeEgreso(proveedorconstruccion,new Date(),42430, medioDePagoconstruccion, cheque,itemsPresupuestoConstruccion);


    //Instancia de lista operacionesDeEgreso
    private final List<OperacionDeEgreso> operacionesDeEgreso = new ArrayList<>(Collections.singletonList(operacionDeEgresoRopaA));


    //Instancia de Entidad Juridica
    private final EntidadJuridica entidadJuridica = new EntidadJuridica ("Grupo 3", "Grupo de disenio", "12-123871328", "Corrientes 1234", "17",
    null, operacionesDeEgreso, null, null);


    //Instancia criterio seleccion de proveedor
    private final CriterioProveedorMenorValor proveedorMenorValor = new CriterioProveedorMenorValor();


    //Instancias de validadores

    private final ValidarCantidadPresupuestos validarCantidadPresupuestos = new ValidarCantidadPresupuestos();
    private final ValidarCriterioSeleccionProveedor validarCriterioSeleccionProveedor = new ValidarCriterioSeleccionProveedor();
    private final ValidarPresupuestoAsociado validarPresupuestoAsociado = new ValidarPresupuestoAsociado();

    private final List<EstrategiaValidacion> validaciones = new ArrayList<>(Arrays.asList(validarCantidadPresupuestos, validarCriterioSeleccionProveedor, validarPresupuestoAsociado));


    //Instancia de validador de Transparencia
    private final ValidadorTransparencia validadorTransparencia = new ValidadorTransparencia(validaciones);


    @Test
    public void verificarEgresoConPresupuesto(){
        operacionDeEgresoRopaA.setPresupuesto(presupuestoRopaA);
        operacionDeEgresoRopaA.setCriterioSeleccionProveedor(proveedorMenorValor);
        List<OperacionDeEgreso> operacionesDeEgresos = entidadJuridica.getOperacionesDeEgreso();
        validadorTransparencia.setOperacionesAValidar(operacionesDeEgresos);
        operacionDeEgresoRopaA.setValidadorTransparencia(validadorTransparencia);
        Assert.assertTrue(operacionDeEgresoRopaA.validarEgreso());
    }
}