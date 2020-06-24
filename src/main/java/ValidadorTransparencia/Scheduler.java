package ValidadorTransparencia;

import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {

    public static void ejecutarValidadorCadaCiertoTiempo(ValidadorTransparencia unValidador, int tiempo) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                unValidador.validarEgresos();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, tiempo);
    }
}
