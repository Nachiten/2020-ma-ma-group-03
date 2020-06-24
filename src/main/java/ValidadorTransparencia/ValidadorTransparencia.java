package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;

import java.util.*;
import java.util.stream.Collectors;

public class ValidadorTransparencia {
    private List<EstrategiaValidacion> validaciones;
    private List<OperacionDeEgreso> operacionesAValidar;
    private List<OperacionDeEgreso> operacionesValidadasCorrectas;
    private List<OperacionDeEgreso> operacionesValidadasIncorrectas;

    public ValidadorTransparencia(List<EstrategiaValidacion> validaciones, List<OperacionDeEgreso> operacionesAValidar) {
        this.validaciones = validaciones;
        this.operacionesAValidar = operacionesAValidar;
        this.operacionesValidadasCorrectas = new ArrayList<>();
        this.operacionesValidadasIncorrectas = new ArrayList<>();
    }

    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        return validaciones.stream().allMatch(unaValidacion -> unaValidacion.validarEgreso(operacionDeEgreso));
    }

    private void publicarMensaje(OperacionDeEgreso operacionDeEgreso, Boolean resultado){
        operacionDeEgreso.getUsuario().getBandejaDeMensajes().publicarMensaje(resultado);
    }

    public void validarEgresos(){

        if(operacionesAValidar.isEmpty()){
            System.out.print("No hay operaciones de egreso para validar! \n");
            return;
        }

        List<OperacionDeEgreso> operacionesValidadasCorrectamente =  operacionesAValidar.stream().filter(this::validarEgreso).collect(Collectors.toList());

        operacionesValidadasCorrectas.addAll(operacionesValidadasCorrectamente);
        operacionesAValidar.removeAll(operacionesValidadasCorrectamente);

        operacionesValidadasIncorrectas.addAll(operacionesAValidar);
        operacionesAValidar.clear();

        if(operacionesValidadasIncorrectas.isEmpty()){
        System.out.print("Se validaron todas las operaciones de egreso correctamente! \n");
        } else {
        System.out.print("Hay alguna operacion de egreso que no se puede validar! \n");
        }
    }

    public void setOperacionesAValidar(List<OperacionDeEgreso> operacionesAValidar) {
        this.operacionesAValidar = operacionesAValidar;
    }


}
