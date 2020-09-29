package model;

public  class Validacion {

    public Boolean puedenVincularse(OperacionDeIngreso operacionDeIngreso, OperacionDeEgreso operacionDeEgreso) {
        return operacionDeEgreso.getMontoTotal() <= operacionDeIngreso.getMontoSinVincular() && !operacionDeEgreso.fueVinculada();
    }
}