import CriterioSeleccionProveedor.CriterioProveedorMenorValor;
import Operaciones.*;
import ValidadorTransparencia.*;

import java.util.*;

public class Principal {

    public static void main(String[] args) {

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
        OperacionDeEgreso operacionDeEgresoRopaA = new OperacionDeEgreso(new Date(),5600, medioDePagoTarjetaDeCredito, itemsPresupuestoRopaA);
        Presupuesto presupuestoRopaA = new Presupuesto(5600, itemsPresupuestoRopaA);

        OperacionDeEgreso operacionEgresoConstruccion = new OperacionDeEgreso(new Date(),42430, medioDePagoTarjetaDeCredito, itemsPresupuestoConstruccion);
        Presupuesto presupuestoConstruccion = new Presupuesto(42430, itemsPresupuestoConstruccion);

        //Instancia criterio seleccion de proveedor
        CriterioProveedorMenorValor proveedorMenorValor = new CriterioProveedorMenorValor();

        ValidarCantidadPresupuestos validarCantidadPresupuestos = new ValidarCantidadPresupuestos();
        ValidarCriterioSeleccionProveedor validarCriterioSeleccionProveedor = new ValidarCriterioSeleccionProveedor();
        ValidarPresupuestoAsociado validarPresupuestoAsociado = new ValidarPresupuestoAsociado();

        List<EstrategiaValidacion> validaciones = new ArrayList<>(Arrays.asList(validarCantidadPresupuestos, validarCriterioSeleccionProveedor, validarPresupuestoAsociado));

        //Instancia de validador de Transparencia
        List<OperacionDeEgreso> operacionDeEgresosAValidar = new ArrayList<>();
        List<OperacionDeEgreso> operacionDeEgresosValidadas = new ArrayList<>();
        ValidadorTransparencia validadorTransparencia = new ValidadorTransparencia(validaciones, operacionDeEgresosAValidar, 3);

        //Instancia de lista operacionesDeEgreso
        List<OperacionDeEgreso> operacionesDeEgreso = new ArrayList<>(Arrays.asList(operacionEgresoConstruccion, operacionDeEgresoRopaA));


        operacionEgresoConstruccion.agregarPresupuesto(presupuestoConstruccion);
        operacionEgresoConstruccion.setCriterioSeleccionProveedor(proveedorMenorValor);
        operacionEgresoConstruccion.setValidadorTransparencia(validadorTransparencia);

        operacionDeEgresoRopaA.agregarPresupuesto(presupuestoRopaA);
        operacionDeEgresoRopaA.setCriterioSeleccionProveedor(proveedorMenorValor);
        operacionDeEgresoRopaA.setValidadorTransparencia(validadorTransparencia);

        validadorTransparencia.setOperacionesDeEgresoAValidar(operacionesDeEgreso);


        int segundoEnMilisegundos = 1000;

        Scheduler.ejecutarCadaCiertoTiempo(validadorTransparencia, segundoEnMilisegundos * 20);

    }
}