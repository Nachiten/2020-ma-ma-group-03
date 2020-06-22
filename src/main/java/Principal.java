import CriterioSeleccionProveedor.CriterioProveedorMenorValor;
import Entidades.EntidadJuridica;
import Operaciones.*;
import ValidadorTransparencia.*;

import java.util.*;

public class Principal {

    public static void main(String[] args) {
        //ValidarCantidadPresupuestos validacionCantidadPresupuestos = new ValidarCantidadPresupuestos();

        TipoMedioDePago tarjetaDeCredito = new TipoMedioDePago("tarjetaDeCredito");

        MedioDePago medioDePagoconstruccion = new MedioDePago(tarjetaDeCredito, 9468753);

        TipoDocumentoComercial cheque = new TipoDocumentoComercial("cheque");

        DocumentoComercial documentoCheque = new DocumentoComercial(cheque, 4534784);

        Item itemPresupuestoConstruccion1 = new Item("Ladrillos 1 millar", 40000);
        Item itemPresupuestoConstruccion2 = new Item("Cemento x 25KG", 400);
        Item itemPresupuestoConstruccion3 = new Item("Alambre x 5KG", 200);
        Item itemPresupuestoConstruccion4 = new Item("Varilla de hierro", 180);
        Item itemPresupuestoConstruccion5 = new Item("Arena x 10KG", 1650);
        List<Item> itemsPresupuestoConstruccion = new ArrayList<>(Arrays.asList(itemPresupuestoConstruccion1, itemPresupuestoConstruccion2, itemPresupuestoConstruccion3, itemPresupuestoConstruccion4, itemPresupuestoConstruccion5));

        OperacionDeEgreso operacionEgresoConstruccion = new OperacionDeEgreso(new Date(),42430, medioDePagoconstruccion, documentoCheque,itemsPresupuestoConstruccion);

        ValidarCantidadPresupuestos validarCantidadPresupuestos = new ValidarCantidadPresupuestos();
        ValidarCriterioSeleccionProveedor validarCriterioSeleccionProveedor = new ValidarCriterioSeleccionProveedor();
        ValidarPresupuestoAsociado validarPresupuestoAsociado = new ValidarPresupuestoAsociado();

        List<EstrategiaValidacion> validaciones = new ArrayList<>(Arrays.asList(validarCantidadPresupuestos, validarCriterioSeleccionProveedor, validarPresupuestoAsociado));

        //Instancia de validador de Transparencia
        ValidadorTransparencia validadorTransparencia = new ValidadorTransparencia(validaciones);

        //Instancia de lista operacionesDeEgreso
        List<OperacionDeEgreso> operacionesDeEgreso = new ArrayList<>(Collections.singletonList(operacionEgresoConstruccion));

        EntidadJuridica entidadJuridica = new EntidadJuridica ("Grupo 3", "Grupo de disenio", "12-123871328", "Corrientes 1234", "17",
                null, operacionesDeEgreso, null, null);

        TipoDocumentoComercial recibo = new TipoDocumentoComercial("recibo");

        DocumentoComercial unDocumento = new DocumentoComercial(recibo, 43);

        Presupuesto presupuestoConstruccion = new Presupuesto(5600, itemsPresupuestoConstruccion, unDocumento);

        //Instancia criterio seleccion de proveedor
        CriterioProveedorMenorValor proveedorMenorValor = new CriterioProveedorMenorValor();

        operacionEgresoConstruccion.agregarPresupuesto(presupuestoConstruccion);
        operacionEgresoConstruccion.setCriterioSeleccionProveedor(proveedorMenorValor);
        List<OperacionDeEgreso> operacionesDeEgresos = entidadJuridica.getOperacionesDeEgreso();
        validadorTransparencia.setOperacionesAValidar(operacionesDeEgresos);
        operacionEgresoConstruccion.setValidadorTransparencia(validadorTransparencia);

        int segundoEnMilisegundos = 1000;

        validadorTransparencia.ejecutarValidadorCadaCiertoTiempo(segundoEnMilisegundos * 5);

    }
}



