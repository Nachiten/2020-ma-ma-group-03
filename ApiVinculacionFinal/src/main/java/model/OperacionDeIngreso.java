package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperacionDeIngreso {
    private LocalDate fecha;
    private String descripcion;
    private float montoTotal;
    private List<OperacionDeEgreso> operacionesDeEgresoVinculadas = new ArrayList<>();
    private float montoSinVincular;
    private LocalDate periodoAceptacion;
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

    public LocalDate getPeriodoAceptable(){
        return periodoAceptacion;
    }

    public int getIDOperacion() {
        return id;
    }
}

