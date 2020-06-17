package ValidadorTransparencia;

import Operaciones.OperacionDeEgreso;
import Usuarios.Mensaje;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ValidadorTransparencia {
    private List<EstrategiaValidacion> validaciones;
    private List<OperacionDeEgreso> operacionesAValidar;
    private List<OperacionDeEgreso> operacionesValidadas;

    public Boolean validarEgreso(OperacionDeEgreso operacionDeEgreso){
        Boolean validacionCorrecta = validaciones.stream().allMatch(unaValidacion -> unaValidacion.validarEgreso(operacionDeEgreso));
        if(validacionCorrecta){
            operacionesAValidar.add(operacionDeEgreso);
        }
        return validacionCorrecta;
    }

    private void publicarMensaje(OperacionDeEgreso operacionDeEgreso, Boolean resultado){
        operacionDeEgreso.getUsuario().getBandejaDeMensajes().publicarMensaje(new Mensaje(resultado));
    }

    public void validarEgresos(){
         Boolean valiadacionesCorrectas =  operacionesAValidar.stream().allMatch(this::validarEgreso);
         if(valiadacionesCorrectas){
             operacionesValidadas.addAll(operacionesAValidar);
             operacionesAValidar.clear();
         }
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
