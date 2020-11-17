package testApiVinculacion;

import ApiEgresoIngreso.ServicioVinculacionEgresosIngresos;
import domain.entities.operaciones.*;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApiTest {

    LocalDate date1 = LocalDate.of(2020, 10, 30);
    LocalDate date2 = LocalDate.of(2020, 11, 20);
    LocalDate date3 = LocalDate.of(2020, 12, 30);
    LocalDate date4 = LocalDate.of(2020, 11, 25);
    LocalDate date5 = LocalDate.of(1990, 11, 25);

    OperacionDeEgreso operacionDeEgreso1 = new OperacionDeEgreso(1,date1, 109.50f);
    OperacionDeEgreso operacionDeEgreso2 = new OperacionDeEgreso(2,date2, 505.40f);
    OperacionDeEgreso operacionDeEgreso3 = new OperacionDeEgreso(3,date3, 109.72f);
    OperacionDeEgreso operacionDeEgreso4 = new OperacionDeEgreso(4,date4, 109.35f);
    OperacionDeEgreso operacionDeEgreso5 = new OperacionDeEgreso(5, date5, 20.05f);

    OperacionDeIngreso operacionDeIngreso1 = new OperacionDeIngreso("1", 200.50f, date1);
    OperacionDeIngreso operacionDeIngreso2 = new OperacionDeIngreso("2",20.40f, date2);
    OperacionDeIngreso operacionDeIngreso3 = new OperacionDeIngreso("3",220.00f, date3);
    OperacionDeIngreso operacionDeIngreso4 = new OperacionDeIngreso("4",600.35f, date4);

    List<OperacionDeEgreso> operacionDeEgresoList = new ArrayList<>();
    List<OperacionDeIngreso> operacionDeIngresoList = new ArrayList<>();

    ServicioVinculacionEgresosIngresos servicio = ServicioVinculacionEgresosIngresos.instancia();

    List<String> criterios = new ArrayList<>();

    public void agregarOperacionesALista(){
        operacionDeEgresoList.add(operacionDeEgreso1);
        operacionDeEgresoList.add(operacionDeEgreso2);
        operacionDeEgresoList.add(operacionDeEgreso3);
        operacionDeEgresoList.add(operacionDeEgreso4);
        operacionDeEgresoList.add(operacionDeEgreso5);
        operacionDeIngresoList.add(operacionDeIngreso1);
        operacionDeIngresoList.add(operacionDeIngreso2);
        operacionDeIngresoList.add(operacionDeIngreso3);
        operacionDeIngresoList.add(operacionDeIngreso4);
    }

    public void agregarFechasMinimasYMaximaAIngresos(){
        operacionDeIngreso1.setPeriodoAceptacion(date3);
        operacionDeIngreso2.setPeriodoAceptacion(date3);
        operacionDeIngreso3.setPeriodoAceptacion(date2);
        operacionDeIngreso4.setPeriodoAceptacion(date3);
    }

    //ACA TENGO QUE PEDIRLE A LA API QUE ME HAGA LO QUE TIENE QUE HACER.
    //VER SI FUNCIONA LA API CON ESTO.
    //TESTS DE ASOCIACION

    @Test
    public void ordenValorPrimeroEgreso() throws IOException {
        agregarOperacionesALista();
        criterios.add("ordenValorPrimeroEgreso");
        List<OperacionDeIngreso> ingresos = servicio.ejecutarVinculacion(operacionDeEgresoList, operacionDeIngresoList, criterios);
    }

    @Test
    public void ordenValorPrimeroIngreso() throws IOException {
        agregarOperacionesALista();
        criterios.add("ordenValorPrimeroIngreso");
        List<OperacionDeIngreso> ingresos = servicio.ejecutarVinculacion(operacionDeEgresoList, operacionDeIngresoList, criterios);
    }

    @Test
    public void criterioFecha() throws IOException {
        agregarFechasMinimasYMaximaAIngresos();
        agregarOperacionesALista();
        criterios.add("fecha");
        List<OperacionDeIngreso> ingresos = servicio.ejecutarVinculacion(operacionDeEgresoList, operacionDeIngresoList, criterios);
    }
    @Test
    public void criterioMix() throws IOException {
        agregarFechasMinimasYMaximaAIngresos();
        agregarOperacionesALista();
        criterios.add("mix");
        criterios.add("fecha");
        criterios.add("ordenValorPrimeroEgreso");
        List<OperacionDeIngreso> ingresos = servicio.ejecutarVinculacion(operacionDeEgresoList, operacionDeIngresoList, criterios);
    }
}



