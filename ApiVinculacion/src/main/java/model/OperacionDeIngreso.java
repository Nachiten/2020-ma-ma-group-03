package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperacionDeIngreso {
    private LocalDate fecha;
    private float montoTotal;
    private List<OperacionDeEgreso> operacionesDeEgresoVinculadas = new ArrayList<>();
    private float montoSinVincular;
    private LocalDate fechaMinima;
    private LocalDate fechaMaxima;
    private int id;


    public void restarMontoSinVincular(float monto){
        montoSinVincular -= monto;
    }

    public void asociarEgreso(OperacionDeEgreso operacionDeEgreso){
        this.restarMontoSinVincular(operacionDeEgreso.getMontoTotal());
        operacionesDeEgresoVinculadas.add(operacionDeEgreso);
    }

    //Getters

    public float getMontoTotal(){
        return montoTotal;
    }


    public float getMontoSinVincular(){
        return montoSinVincular;
    }

    public LocalDate getFecha(){
        return fecha;
    }

    public LocalDate getFechaMinima(){
        return fechaMinima;
    }

    public LocalDate getFechaMaxima(){
        return fechaMaxima;
    }

    public int getIDOperacion() {
        return id;
    }
}

