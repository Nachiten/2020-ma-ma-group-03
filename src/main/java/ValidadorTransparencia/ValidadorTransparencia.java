package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;

import java.util.*;
import java.util.stream.Collectors;

public class ValidadorTransparencia {
    private List<EstrategiaValidacion> validaciones;
    private List<OperacionDeEgreso> operacionesAValidar;
    private List<OperacionDeEgreso> operacionesValidadas;

    public ValidadorTransparencia(List<EstrategiaValidacion> validaciones, List<OperacionDeEgreso> operacionesAValidar, List<OperacionDeEgreso> operacionesValidadas) {
        this.validaciones = validaciones;
        this.operacionesAValidar = operacionesAValidar;
        this.operacionesValidadas = operacionesValidadas;
    }

    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        return validaciones.stream().allMatch(unaValidacion -> unaValidacion.validarEgreso(operacionDeEgreso));
    }

    private void publicarMensaje(OperacionDeEgreso operacionDeEgreso, Boolean resultado){
        operacionDeEgreso.getUsuario().getBandejaDeMensajes().publicarMensaje(operacionDeEgreso, resultado);
    }

    public void validarEgresos(){

        if(operacionesAValidar.isEmpty()){
            System.out.print("No hay operaciones de egreso para validar! \n");
            return;
        }

        List<OperacionDeEgreso> operacionesValidadasCorrectamente =  operacionesAValidar.stream().filter(this::validarEgreso).collect(Collectors.toList());

        operacionesValidadas.addAll(operacionesValidadasCorrectamente);
        operacionesAValidar.removeAll(operacionesValidadasCorrectamente);

        if(operacionesAValidar.isEmpty()){
        System.out.print("Se validaron todas las operaciones de egreso correctamente! \n");
        } else {
        System.out.print("Hay alguna operacion de egreso que no se puede validar! \n");
    }
}

    public void setOperacionesAValidar(List<OperacionDeEgreso> operacionesAValidar) {
        this.operacionesAValidar = operacionesAValidar;
    }

    public void ejecutarValidadorCadaCiertoTiempo(int tiempo) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                validarEgresos();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, tiempo);
    }
}
