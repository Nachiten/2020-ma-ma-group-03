package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ValidadorTransparencia {
    private List<EstrategiaValidacion> validaciones;
    private List<OperacionDeEgreso> operacionesAValidar;
    private List<OperacionDeEgreso> operacionesValidadas;

    public ValidadorTransparencia(List<EstrategiaValidacion> validaciones) {
        this.validaciones = validaciones;
    }

    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        return validaciones.stream().allMatch(unaValidacion -> unaValidacion.validarEgreso(operacionDeEgreso));
    }

    private void publicarMensaje(OperacionDeEgreso operacionDeEgreso, Boolean resultado){
        operacionDeEgreso.getUsuario().getBandejaDeMensajes().publicarMensaje(operacionDeEgreso, resultado);
    }

    public void validarEgresos(){
         Boolean valiadacionesCorrectas =  operacionesAValidar.stream().allMatch(this::validarEgreso);
         if(valiadacionesCorrectas){
             operacionesValidadas.addAll(operacionesAValidar);
             operacionesAValidar.clear();
         }
    }

    public void setOperacionesAValidar(List<OperacionDeEgreso> operacionesAValidar) {
        this.operacionesAValidar = operacionesAValidar;
    }

    public void ejecutarValidadorEnDeterminadoTiempo(int tiempo) {
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
