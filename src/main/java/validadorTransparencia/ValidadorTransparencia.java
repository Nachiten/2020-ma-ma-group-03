package validadorTransparencia;

import domain.entities.operaciones.OperacionDeEgreso;

import java.util.*;
import java.util.stream.Collectors;

public class ValidadorTransparencia implements SchedulerFunction{
    private List<EstrategiaValidacion> validaciones;
    private List<OperacionDeEgreso> operacionesDeEgresoAValidar;
    private int cantidadDeChancesParaValidar;

    public ValidadorTransparencia(List<EstrategiaValidacion> validaciones, List<OperacionDeEgreso> operacionesDeEgresoAValidar, int cantidadDeChancesParaValidar) {
        this.validaciones = validaciones;
        this.operacionesDeEgresoAValidar = operacionesDeEgresoAValidar;
        this.cantidadDeChancesParaValidar = cantidadDeChancesParaValidar;
    }

    public Boolean hayQueValidar(OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getCantidadDeVecesValidada() < cantidadDeChancesParaValidar && !operacionDeEgreso.esValida();
    }

    public boolean esOperacionValida(OperacionDeEgreso operacionDeEgreso){
        return validaciones.stream().allMatch(unaValidacion -> unaValidacion.validarEgreso(operacionDeEgreso));
    }

    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){

      if(this.hayQueValidar(operacionDeEgreso))
      operacionDeEgreso.fuiValidada();

      if(this.esOperacionValida(operacionDeEgreso))
          operacionDeEgreso.soyValida();

      publicarMensaje(operacionDeEgreso, operacionDeEgreso.esValida());

      return operacionDeEgreso.esValida();

    }

    private void publicarMensaje(OperacionDeEgreso operacionDeEgreso, Boolean resultadoValidacion){

        String identificacion = "Fecha de la operacion: " + operacionDeEgreso.getFecha().toString() + ", Monto: " + operacionDeEgreso.getMontoTotal() + ", ID: " + operacionDeEgreso.getIdOperacion();
        operacionDeEgreso.getUsuario().publicarMensajeEnBandejaDeMensajes(identificacion, resultadoValidacion);
    }

    public void setOperacionesDeEgresoAValidar(List<OperacionDeEgreso> operacionesDeEgresoAValidar) {
        this.operacionesDeEgresoAValidar = operacionesDeEgresoAValidar;
    }

    public void validarEgresos(){
       operacionesDeEgresoAValidar.stream().filter(this::validarEgreso).collect(Collectors.toList());
    }

    @Override
    public void ejecutarse() {
        System.out.println("Ejecutando validador...");
        validarEgresos();
    }
}