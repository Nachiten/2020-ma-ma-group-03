package miTest;

import Entidades.EntidadJuridica;
import Operaciones.*;
import ValidadorTransparencia.*;
import Vendedor.Proveedor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ValidadorTransparenciaTest {

    //Random  lista de items
    private Item itemPrepCalzado1 = new Item("Botas mujer", 3500);
    private Item itemPrepCalzado2 = new Item("Botas hombre", 3000);
    private Item itemPrepCalzado3 = new Item("Zapatos de tacon", 4500);
    private Item itemPrepCalzado4 = new Item("Sandalias romanas", 2500);
    private Item itemPrepCalzado5 = new Item("Zapatillas tenis", 3700);
    private List<Item> itemsPresupuestoCalzado = new ArrayList<>(Arrays.asList(itemPrepCalzado1, itemPrepCalzado2, itemPrepCalzado3,itemPrepCalzado4, itemPrepCalzado5));

    private Item itemPrepOficina1 = new Item("Escritorio", 15000);
    private Item itemPrepOficina2 = new Item("Silla", 6000);
    private Item itemPrepOficina3 = new Item("Papelera", 3000);
    private Item itemPrepOficina4 = new Item("Porta documentos", 15000);
    private Item itemPrepOficina5 = new Item("Lapiceros", 500);
    private List<Item> itemsPresupuestoOficina = new ArrayList<>(Arrays.asList(itemPrepOficina1, itemPrepOficina2, itemPrepOficina3,itemPrepOficina4, itemPrepOficina5));

    //Random documentos comerciales
    private DocumentoComercial documentoComercialRecibo = new DocumentoComercial("Recibo", 8678478);
    private DocumentoComercial documentoComercialCheque = new DocumentoComercial("Cheque", 4534784);
    private DocumentoComercial documentoComercialRemito = new DocumentoComercial("Remito", 4547888);
    private DocumentoComercial documentoComercialFacturaTipoB = new DocumentoComercial("Factura Tipo B", 4547889);


    //Items ropa A

    private Item itemPrepRopaA1 = new Item("Remera Talle L", 1000);
    private Item itemPrepRopaA2 = new Item("Pantalon Talle 42", 1550);
    private Item itemPrepRopaA3 = new Item("Remera Talle S", 600);
    private Item itemPrepRopaA4 = new Item("Remera Talle M", 800);
    private Item itemPrepRopaA5 = new Item("Pantalon Talle 44", 1650);
    private List<Item> itemsPresupuestoRopaA = new ArrayList<>(Arrays.asList(itemPrepRopaA1, itemPrepRopaA2, itemPrepRopaA3,itemPrepRopaA4, itemPrepRopaA5));

    //Egreso Ropa A

    private DocumentoComercial documentoComercialPrepRopaFacturaA = new DocumentoComercial("Factura", 8665478);
    private Proveedor proveedorRopaA = new Proveedor("Indumentaria deportiva Bs As",58462146,"4563");
    private MedioDePago medioDePagoEgresoRopaA = new MedioDePago("Tarjeta de credito", 9468753);
    private OperacionDeEgreso opEgresoRopaA = new OperacionDeEgreso(proveedorRopaA,new Date(),5600, medioDePagoEgresoRopaA, documentoComercialPrepRopaFacturaA,itemsPresupuestoRopaA);

    //Presupuesto de ropa A

    private Presupuesto presupuestoRopaA = new Presupuesto(5600, itemsPresupuestoRopaA, documentoComercialPrepRopaFacturaA);


    //Items de Construccion

    private Item itemPrepConstruccion1 = new Item("Ladrillos 1 millar", 40000);
    private Item itemPrepConstruccion2 = new Item("Cemento x 25KG", 400);
    private Item itemPrepConstruccion3 = new Item("Alambre x 5KG", 200);
    private Item itemPrepConstruccion4 = new Item("Varilla de hierro", 180);
    private Item itemPrepConstruccion5 = new Item("Arena x 10KG", 1650);
    private List<Item> itemsPresupuestoConstruccion = new ArrayList<>(Arrays.asList(itemPrepConstruccion1, itemPrepConstruccion2, itemPrepConstruccion3,itemPrepConstruccion4, itemPrepConstruccion5));

    //Egreso construccion

    private Proveedor proveedorconstruccion = new Proveedor("Constructora Comaf",12762146,"876");
    private MedioDePago medioDePagoconstruccion = new MedioDePago("Cheque", 7941753);
    private OperacionDeEgreso opEgresoconstruccion = new OperacionDeEgreso(proveedorconstruccion,new Date(),42430, medioDePagoconstruccion, documentoComercialCheque,itemsPresupuestoConstruccion);

    //Presupuesto de ropa B

    private Item itemPrepRopaB1 = new Item("Camisa Talle L", 1100);
    private Item itemPrepRopaB2 = new Item("Pantalon Talle 42", 1550);
    private Item itemPrepRopaB3 = new Item("Camisa Talle S", 650);
    private Item itemPrepRopaB4 = new Item("Camisa Talle M", 850);
    private Item itemPrepRopaB5 = new Item("Pantalon Talle 44", 1650);
    private List<Item> itemsPresupuestoRopaB = new ArrayList<>(Arrays.asList(itemPrepRopaB1, itemPrepRopaB2, itemPrepRopaB3,itemPrepRopaB4, itemPrepRopaB5));
    private DocumentoComercial documentoComercialPrepRopaFacturaB = new DocumentoComercial("Factura", 8665478);
    private Presupuesto presupuestoRopaB = new Presupuesto(5800, itemsPresupuestoRopaB, documentoComercialPrepRopaFacturaB);


    //Instancia de lista operacionesDeEgreso
    private List<OperacionDeEgreso> operacionesDeEgreso = new ArrayList<>(Arrays.asList(opEgresoRopaA));

    //Instancia de Entidad Juridica
    private EntidadJuridica entidadJuridica = new EntidadJuridica ("Grupo 3", "Grupo de disenio", "12-123871328", "Corrientes 1234", "17",
    null, operacionesDeEgreso, null, null);

    //Instancias de validadores

    private ValidarCantidadPresupuestos validarCantidadPresupuestos = new ValidarCantidadPresupuestos();
    private ValidarCriterioSeleccionProveedor validarCriterioSeleccionProveedor = new ValidarCriterioSeleccionProveedor();
    private ValidarPresupuestoAsociado validarPresupuestoAsociado = new ValidarPresupuestoAsociado();

    private List<EstrategiaValidacion> validaciones = new ArrayList<>(Arrays.asList(validarCantidadPresupuestos, validarCriterioSeleccionProveedor, validarPresupuestoAsociado));


    //Instancia de validador de Transparencia
    private ValidadorTransparencia validadorTransparencia = new ValidadorTransparencia(validaciones);


    @Test
    public void verificarEgresoConPresupuesto(){
        opEgresoRopaA.setPresupuesto(presupuestoRopaA);
        List<OperacionDeEgreso> operacionesDeEgresos = entidadJuridica.getOperacionesDeEgreso();
        validadorTransparencia.setOperacionesAValidar(operacionesDeEgresos);
        opEgresoRopaA.setValidadorTransparencia(validadorTransparencia);
        Assert.assertTrue(opEgresoRopaA.validarEgreso());
    }
}
