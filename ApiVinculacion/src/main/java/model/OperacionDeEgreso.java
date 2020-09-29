package model;

import java.time.LocalDate;

public class OperacionDeEgreso {

    private int IDOperacion;
    private LocalDate fecha;
    private float montoTotal;
    private int operacionDeIngresoId;
    private boolean fueVinculada;

    public void asociarOperacionDeIngreso(OperacionDeIngreso unaOperacionDeIngreso) {
        this.operacionDeIngresoId = unaOperacionDeIngreso.getIDOperacion();
        this.fueVinculada = true;
    }

    //GETTERS

    public LocalDate getFecha() {
        return fecha;
    }

    public int getIDOperacion() {
        return IDOperacion;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public int getOperacionDeIngreso() {
        return operacionDeIngresoId;
    }

    public Boolean fueVinculada(){
        return fueVinculada;
    }
}