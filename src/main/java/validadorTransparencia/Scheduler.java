package validadorTransparencia;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    public static void ejecutarEnDiaYHorario(SchedulerFunction object, int diaSemana, int hora) {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("TimerTask | Se ejecuta automaticamente el validador.");
                object.ejecutarse();
            }
        };

        Calendar horario = Calendar.getInstance();
        //horario.set(Calendar.DAY_OF_MONTH, LocalDateTime.now().getDayOfMonth());

        // Dia elegido
        horario.set(Calendar.DAY_OF_WEEK, diaSemana);
        // Hora elegida
        horario.set(Calendar.HOUR_OF_DAY, hora);
        horario.set(Calendar.DAY_OF_MONTH, LocalDateTime.now().getDayOfMonth() + 1);
        // Siempre es la hora :00
        horario.set(Calendar.MINUTE, 0);
        horario.set(Calendar.SECOND, 0);

        System.out.println("El validador se ejecutará el dia [" + deEnteroADia(diaSemana) + "] y en la hora [" + hora + "]");
        timer.schedule(timerTask, horario.getTime(), TimeUnit.MILLISECONDS.convert(7, TimeUnit.DAYS)); // El periodo es cada 7 dias
    }

    private static String deEnteroADia(int dia){
        switch(dia){
            case Calendar.MONDAY:
                return "Lunes";
            case Calendar.TUESDAY:
                return "Martes";
            case Calendar.WEDNESDAY:
                return "Miercoles";
            case Calendar.THURSDAY:
                return "Jueves";
            case Calendar.FRIDAY:
                return "Viernes";
            case Calendar.SATURDAY:
                return "Sabado";
            case Calendar.SUNDAY:
                return "Domingo";
            default:
                return null;
        }
    }
}