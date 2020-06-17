package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;
import Operaciones.Presupuesto;
import java.util.stream.Stream;

public class ValidarPresupuestoAsociado extends EstrategiaValidacion{

    @Override
    public boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
    Stream<Presupuesto> presupuestos = operacionDeEgreso.getPresupuestos().stream().filter(unPresupuesto -> esPresupuestoAsociado(operacionDeEgreso, unPresupuesto));
    return presupuestos.count() == 1;
    }

    private boolean esPresupuestoAsociado(OperacionDeEgreso operacionDeEgreso, Presupuesto presupuesto){
        return montosIguales(operacionDeEgreso, presupuesto) && documentoComercialIguales(operacionDeEgreso, presupuesto) && itemsIguales(operacionDeEgreso, presupuesto);
    }

    private boolean montosIguales(OperacionDeEgreso operacionDeEgreso, Presupuesto presupuesto){
        return operacionDeEgreso.getMontoTotal() == presupuesto.getMontoTotal();
    }

    private boolean itemsIguales(OperacionDeEgreso operacionDeEgreso, Presupuesto presupuesto){
        return operacionDeEgreso.getItems().containsAll(presupuesto.getItems()) && presupuesto.getItems().containsAll(operacionDeEgreso.getItems());
    }

    private boolean documentoComercialIguales(OperacionDeEgreso operacionDeEgreso, Presupuesto presupuesto){
        return operacionDeEgreso.getDocumentoComercial() == presupuesto.getDocumentoComercial();
    }
}
