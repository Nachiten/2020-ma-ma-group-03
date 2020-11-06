package validadorTransparencia;

import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {

    public static void ejecutarCadaCiertoTiempo(SchedulerFunction object, int tiempo) {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Voy a ejecutar el validador.");
                object.ejecutarse();
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, tiempo);

        //timerTask.cancel();
    }
}