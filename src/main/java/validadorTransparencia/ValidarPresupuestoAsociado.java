package validadorTransparencia;

import domain.entities.operaciones.Item;
import domain.entities.operaciones.OperacionDeEgreso;
import domain.entities.operaciones.Presupuesto;

import java.util.List;
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
        List<Item> itemsEgreso = operacionDeEgreso.getItems();
        List<Item> itemsPresupuesto = presupuesto.getItems();
        Boolean resultado = null;
        boolean resultadoParcial;

        for(Item item: itemsEgreso){
            String descripcion = item.getDescripcion();
            int cantidad = item.getCantidad();
            float precioUnitario = item.getPrecioUnitario();

            for(Item itemPresupuesto: itemsPresupuesto){
                String descripcionItemPresupuesto = itemPresupuesto.getDescripcion();
                int cantidadItemPresupuesto = itemPresupuesto.getCantidad();
                float precioItemPrespuesto = item.getPrecioUnitario();
                resultadoParcial = descripcion.equals(descripcionItemPresupuesto) && cantidad == cantidadItemPresupuesto && precioUnitario == precioItemPrespuesto;

                if(resultadoParcial){
                    resultado = true;
                    break;
                }
                resultado = false;
            }
        }
        return resultado;
    }

    private Boolean documentoComercialIguales(OperacionDeEgreso operacionDeEgreso, Presupuesto presupuesto){
        return operacionDeEgreso.getDocumentoComercial() == presupuesto.getDocumentoComercial();
    }
}
