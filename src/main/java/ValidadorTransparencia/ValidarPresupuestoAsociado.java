package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;
import Operaciones.Presupuesto;

public class ValidarPresupuestoAsociado extends EstrategiaValidacion{

    @Override
    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        Presupuesto presupuesto = operacionDeEgreso.getPresupuesto();
        return esPresupuestoAsociado(operacionDeEgreso, presupuesto);
    }

    private Boolean esPresupuestoAsociado(OperacionDeEgreso operacionDeEgreso, Presupuesto presupuesto){
        return montosIguales(operacionDeEgreso, presupuesto) && documentoComercialIguales(operacionDeEgreso, presupuesto) && itemsIguales(operacionDeEgreso, presupuesto);
    }

    private Boolean montosIguales(OperacionDeEgreso operacionDeEgreso, Presupuesto presupuesto){
        return operacionDeEgreso.getMontoTotal() == presupuesto.getMontoTotal();
    }

    private Boolean itemsIguales(OperacionDeEgreso operacionDeEgreso, Presupuesto presupuesto){
        return operacionDeEgreso.getItems().containsAll(presupuesto.getItems()) && presupuesto.getItems().containsAll(operacionDeEgreso.getItems());
    }

    private Boolean documentoComercialIguales(OperacionDeEgreso operacionDeEgreso, Presupuesto presupuesto){
        return operacionDeEgreso.getDocumentoComercial() == presupuesto.getDocumentoComercial();
    }
}
