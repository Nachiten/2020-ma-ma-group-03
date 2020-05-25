package Model.Entidades;

import Model.Operaciones.OperacionDeEgreso;

public class Entidad {

    public String nombreFicticio;

    //aca suponemos que los dos tipos de entidades van a realizar la operacion de la misma manera.
    //Sino la clase seria abstracta, dejaria de tener en el metodo la implementación, para que cada clase lo haga como sea que necesite.

    public void realizarOperacionDeEgreso(OperacionDeEgreso operacionDeEgreso) {
        //TODO implementacion.
    }
}
