package validadorTransparencia;

import domain.entities.operaciones.OperacionDeEgreso;
import domain.entities.operaciones.Presupuesto;

import java.util.Optional;

public class ValidarPresupuestoAsociado extends EstrategiaValidacion{

    @Override
    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){

    Optional<Presupuesto> presupuesto = operacionDeEgreso.getPresupuestos().stream().filter((unPresupuesto -> esPresupuestoAsociado(operacionDeEgreso, unPresupuesto))).findAny();
    return presupuesto.isPresent();
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
