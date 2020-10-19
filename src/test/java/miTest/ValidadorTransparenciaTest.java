package miTest;

import domain.entities.apiMercadoLibre.DireccionPostal;
import criterioSeleccionProveedor.CriterioProveedorMenorValor;
import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.*;
import validadorTransparencia.*;
import domain.entities.vendedor.Proveedor;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
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

    //Tipo documento comercial
    private final TipoDocumentoComercial recibo = new TipoDocumentoComercial("recibo");
    private final TipoDocumentoComercial cheque = new TipoDocumentoComercial("cheque");
    private final TipoDocumentoComercial remito = new TipoDocumentoComercial("remito");
    private final TipoDocumentoComercial facturaTipoB= new TipoDocumentoComercial("facturaTipoB");
    //Documentos comerciales
    private final DocumentoComercial documentoRecibo = new DocumentoComercial(recibo, 8678478);
    private final DocumentoComercial documentoCheque = new DocumentoComercial(cheque, 4534784);
    private final DocumentoComercial documentoRemito = new DocumentoComercial(remito, 4547888);
    private final DocumentoComercial documentoFacturaTipoB = new DocumentoComercial(facturaTipoB, 4547889);


    //Proveedores
    private final Proveedor indumentariaDeportivaBsAs = new Proveedor("Indumentaria deportiva Bs As",58462146, new DireccionPostal());
    private final Proveedor proveedor2 = new Proveedor("Constructora Comaf",12762146, new DireccionPostal());

   // TipoMedioDePago
    private final TipoMedioDePago tarjetaDeCredito = new TipoMedioDePago("tarjetaDeCredito");

    //Medios de Pago

    private final MedioDePago medioDePagoTarjetaDeCredito = new MedioDePago(tarjetaDeCredito, 9468753);

    //Items Ropa A

    private final Item itemPresupuestoRopaA1 = new Item("Remera Talle L", 1000);
    private final Item itemPresupuestoRopaA2 = new Item("Pantalon Talle 42", 1550);
    private final Item itemPresupuestoRopaA3 = new Item("Remera Talle S", 600);
    private final Item itemPresupuestoRopaA4 = new Item("Remera Talle M", 800);
    private final Item itemPresupuestoRopaA5 = new Item("Pantalon Talle 44", 1650);
    private final List<Item> itemsPresupuestoRopaA = new ArrayList<>(Arrays.asList(itemPresupuestoRopaA1, itemPresupuestoRopaA2, itemPresupuestoRopaA3, itemPresupuestoRopaA4, itemPresupuestoRopaA5));


    List<Presupuesto> presupuestos = new ArrayList<>();

    //Egreso Ropa A

    private final DocumentoComercial documentoComercialRopaA = documentoRecibo;
    private final Proveedor proveedorRopaA = indumentariaDeportivaBsAs;
    private final MedioDePago medioDePagoRopaA = medioDePagoTarjetaDeCredito;
    private final OperacionDeEgreso operacionDeEgresoRopaA = new OperacionDeEgreso(LocalDate.now(),5600, medioDePagoRopaA, itemsPresupuestoRopaA);


    private final Presupuesto presupuestoRopaA = new Presupuesto(5600, itemsPresupuestoRopaA, operacionDeEgresoRopaA);
    private final Presupuesto presupuestoRopaAOtroMonto = new Presupuesto(1700, itemsPresupuestoRopaA, operacionDeEgresoRopaA);
    private final Presupuesto presupuestoRopaAOtrosItems = new Presupuesto(5600, itemsPresupuestoOficina, operacionDeEgresoRopaA);
    private final Presupuesto presupuestoRopaAConDistintoDocumento = new Presupuesto(5600, itemsPresupuestoRopaA, operacionDeEgresoRopaA);
    //Items de Ropa B

    private final Item itemPresupuestoRopaB1 = new Item("Camisa Talle L", 1100);
    private final Item itemPresupuestoRopaB2 = new Item("Pantalon Talle 42", 1550);
    private final Item itemPresupuestoRopaB3 = new Item("Camisa Talle S", 650);
    private final Item itemPresupuestoRopaB4 = new Item("Camisa Talle M", 850);
    private final Item itemPresupuestoRopaB5 = new Item("Pantalon Talle 44", 1650);
    private final List<Item> itemsPresupuestoRopaB = new ArrayList<>(Arrays.asList(itemPresupuestoRopaB1, itemPresupuestoRopaB2, itemPresupuestoRopaB3, itemPresupuestoRopaB4, itemPresupuestoRopaB5));

    //Presupuesto Ropa B

    private final DocumentoComercial documentoComercialRopaB = documentoCheque;
    private final Presupuesto presupuestoRopaB = new Presupuesto(5800, itemsPresupuestoRopaB, operacionDeEgresoRopaA);


    //Items de Construccion

    private final Item itemPresupuestoConstruccion1 = new Item("Ladrillos 1 millar", 40000);
    private final Item itemPresupuestoConstruccion2 = new Item("Cemento x 25KG", 400);
    private final Item itemPresupuestoConstruccion3 = new Item("Alambre x 5KG", 200);
    private final Item itemPresupuestoConstruccion4 = new Item("Varilla de hierro", 180);
    private final Item itemPresupuestoConstruccion5 = new Item("Arena x 10KG", 1650);
    private final List<Item> itemsPresupuestoConstruccion = new ArrayList<>(Arrays.asList(itemPresupuestoConstruccion1, itemPresupuestoConstruccion2, itemPresupuestoConstruccion3, itemPresupuestoConstruccion4, itemPresupuestoConstruccion5));

    //Egreso construccion
    private final MedioDePago medioDePagoconstruccion = medioDePagoTarjetaDeCredito;
    private final OperacionDeEgreso operacionEgresoConstruccion = new OperacionDeEgreso(LocalDate.now(),42430, medioDePagoconstruccion, itemsPresupuestoConstruccion);


    //Instancia de lista operacionesDeEgreso
    private final List<OperacionDeEgreso> operacionesDeEgreso = new ArrayList<>(Collections.singletonList(operacionDeEgresoRopaA));



    //Instancia de Entidad Juridica
    private final EntidadJuridica entidadJuridica = new EntidadJuridica ("Grupo 3",  "12-123871328", null, "17");


    //Instancia criterio seleccion de proveedor
    private final CriterioProveedorMenorValor proveedorMenorValor = new CriterioProveedorMenorValor();


    //Instancias de validadores

    private final ValidarCantidadPresupuestos validarCantidadPresupuestos = new ValidarCantidadPresupuestos();
    private final ValidarCriterioSeleccionProveedor validarCriterioSeleccionProveedor = new ValidarCriterioSeleccionProveedor();
    private final ValidarPresupuestoAsociado validarPresupuestoAsociado = new ValidarPresupuestoAsociado();

    private final List<EstrategiaValidacion> validaciones = new ArrayList<>(Arrays.asList(validarCantidadPresupuestos, validarCriterioSeleccionProveedor, validarPresupuestoAsociado));


    //Instancia de validador de Transparencia
    List<OperacionDeEgreso> operacionDeEgresosSinValidar = new ArrayList<>();
    private final ValidadorTransparencia validadorTransparencia = new ValidadorTransparencia(validaciones, operacionDeEgresosSinValidar, 3);

    public ValidadorTransparenciaTest() throws IOException {
    }

    public void asociarOperacionConPresupuesto(OperacionDeEgreso operacionDeEgreso, Presupuesto presupuesto){
        operacionDeEgreso.agregarPresupuesto(presupuesto);
        operacionDeEgreso.setCriterioSeleccionProveedor(proveedorMenorValor);
        operacionDeEgreso.setCantidadPresupuestosRequerida(1);
        List<OperacionDeEgreso> operacionesDeEgresos = entidadJuridica.getOperacionesDeEgreso();
        validadorTransparencia.setOperacionesDeEgresoAValidar(operacionesDeEgresos);
        operacionDeEgreso.setValidadorTransparencia(validadorTransparencia);
    }


    @Test
    public void verificarEgresoConPresupuesto(){
        asociarOperacionConPresupuesto(operacionDeEgresoRopaA, presupuestoRopaA);
        Assert.assertTrue(operacionDeEgresoRopaA.validarEgreso());
    }

    @Test
    public void verificarEgresoSinPresupuesto() {
        operacionDeEgresoRopaA.setCriterioSeleccionProveedor(proveedorMenorValor);
        List<OperacionDeEgreso> operacionesDeEgresos = entidadJuridica.getOperacionesDeEgreso();
        validadorTransparencia.setOperacionesDeEgresoAValidar(operacionesDeEgresos);
        operacionDeEgresoRopaA.setValidadorTransparencia(validadorTransparencia);
        Assert.assertFalse(operacionDeEgresoRopaA.validarEgreso());
    }

    @Test
    public void verificarQueTengaPresupuestoErroneo(){
      asociarOperacionConPresupuesto(operacionDeEgresoRopaA, presupuestoRopaB);
      Assert.assertFalse(operacionDeEgresoRopaA.validarEgreso());

    }

    @Test
    public void verificarQueTengaDistintoMonto(){
        asociarOperacionConPresupuesto(operacionDeEgresoRopaA, presupuestoRopaAOtroMonto);
        Assert.assertFalse(operacionDeEgresoRopaA.validarEgreso());
     }

    @Test
    public void verificarQueTengaDistintosItems(){
        asociarOperacionConPresupuesto(operacionDeEgresoRopaA,presupuestoRopaAOtrosItems);
        Assert.assertFalse(operacionDeEgresoRopaA.validarEgreso());
     }

    @Test
    public void verificarQueTengaDistintoDocumentoComercial(){
        presupuestoRopaAConDistintoDocumento.setDocumentoComercial(documentoComercialRopaB);
        asociarOperacionConPresupuesto(operacionDeEgresoRopaA,presupuestoRopaAConDistintoDocumento);
        Assert.assertFalse(operacionDeEgresoRopaA.validarEgreso());
    }
}