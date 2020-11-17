package model;

public class ValidacionPeriodo extends Validacion {

    @Override
    public Boolean puedenVincularse(OperacionDeIngreso operacionDeIngreso, OperacionDeEgreso operacionDeEgreso){
        return super.puedenVincularse(operacionDeIngreso,operacionDeEgreso) && estaEnPeriodoAceptable(operacionDeIngreso,operacionDeEgreso);
    }

    public Boolean estaEnPeriodoAceptable(OperacionDeIngreso operacionDeIngreso,OperacionDeEgreso operacionDeEgreso){
        return estaEnDiaAceptable(operacionDeIngreso, operacionDeEgreso) && estaEnMesAceptable(operacionDeIngreso, operacionDeEgreso) && estaEnElMismoAnio(operacionDeIngreso, operacionDeEgreso);
    }

    public Boolean estaEnDiaAceptable(OperacionDeIngreso operacionDeIngreso,OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getFecha().getDayOfMonth() <= operacionDeIngreso.getPeriodoAceptable().getDayOfMonth();
    }

    public Boolean estaEnMesAceptable(OperacionDeIngreso operacionDeIngreso,OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getFecha().getMonthValue() <= operacionDeIngreso.getPeriodoAceptable().getMonthValue();
    }

    public Boolean estaEnElMismoAnio(OperacionDeIngreso operacionDeIngreso, OperacionDeEgreso operacionDeEgreso){
        return operacionDeEgreso.getFecha().getYear() == operacionDeIngreso.getPeriodoAceptable().getYear();
    }
}