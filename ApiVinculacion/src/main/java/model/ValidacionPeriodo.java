package model;

public class ValidacionPeriodo extends Validacion {

    @Override
    public Boolean puedenVincularse(OperacionDeIngreso operacionDeIngreso, OperacionDeEgreso operacionDeEgreso){
        return super.puedenVincularse(operacionDeIngreso,operacionDeEgreso) && estaEnPeriodoAceptable(operacionDeIngreso,operacionDeEgreso);
    }

    public Boolean estaEnPeriodoAceptable(OperacionDeIngreso operacionDeIngreso,OperacionDeEgreso operacionDeEgreso){
        return estaEntreFechaAceptable(operacionDeIngreso, operacionDeEgreso) && estaEnElMismoAnio(operacionDeIngreso, operacionDeEgreso);
    }

    public Boolean estaEntreFechaAceptable(OperacionDeIngreso operacionDeIngreso,OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getFecha().getMonthValue() >= operacionDeIngreso.getFechaMinima().getMonthValue()  && operacionDeEgreso.getFecha().getMonthValue() <= operacionDeIngreso.getFechaMaxima().getMonthValue();
    }

    public Boolean estaEnElMismoAnio(OperacionDeIngreso operacionDeIngreso, OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getFecha().getYear() == operacionDeIngreso.getFechaMaxima().getYear();
    }
}