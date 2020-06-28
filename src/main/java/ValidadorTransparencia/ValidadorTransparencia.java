package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;

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

      if(hayQueValidar(operacionDeEgreso))
      operacionDeEgreso.fuiValidada();

      if(esOperacionValida(operacionDeEgreso))
          operacionDeEgreso.soyValida();

      publicarMensaje(operacionDeEgreso, operacionDeEgreso.esValida());

      return operacionDeEgreso.esValida();

    }

    private void publicarMensaje(OperacionDeEgreso operacionDeEgreso, Boolean resultado){
        operacionDeEgreso.getUsuario().getBandejaDeMensajes().publicarMensaje(resultado); //No se como distinguir el true o false de que operacion de egreso???
                                                                                          //No se como no acoplarlo
                                                                                          //Como se que revisores son si no se que operacion de egreso es??
    }

    public void setOperacionesDeEgresoAValidar(List<OperacionDeEgreso> operacionesDeEgresoAValidar) {
        this.operacionesDeEgresoAValidar = operacionesDeEgresoAValidar;
    }

    public void validarEgresos(){
        List<OperacionDeEgreso> operacionesValidadasCorrectamente =  operacionesDeEgresoAValidar.stream().filter(this::validarEgreso).collect(Collectors.toList());
    }

    @Override
    public void ejecutarse() {
        validarEgresos();
    }
}
