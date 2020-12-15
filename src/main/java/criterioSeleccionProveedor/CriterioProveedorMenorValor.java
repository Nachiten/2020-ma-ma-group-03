package criterioSeleccionProveedor;

import domain.entities.operaciones.OperacionDeEgreso;
import domain.entities.operaciones.Presupuesto;
import persistencia.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.Optional;

@Entity
@Table (name = "criterioProveedorMenorValor")
public class CriterioProveedorMenorValor extends EntidadPersistente implements CriterioSeleccionProveedor  {

    public Boolean seleccionarProveedor(OperacionDeEgreso operacionDeEgreso) {
        return esPresupuestoDeMenorValor(operacionDeEgreso);
    }

    public Boolean esPresupuestoDeMenorValor(OperacionDeEgreso operacionDeEgreso){

        Optional<Presupuesto> presupuestoMenorValor = operacionDeEgreso.getPresupuestos().stream().min(Comparator.comparing(Presupuesto::getMontoTotal));

        return presupuestoMenorValor.isPresent() && presupuestoMenorValor.get().getMontoTotal() == operacionDeEgreso.getMontoTotal();
    }
}
