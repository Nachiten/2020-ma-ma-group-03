package Entidades;

import TipoEntidadJuridica.Empresa;

public class Afip {

    public static String clasificacion(Empresa empresa){
        return empresa.getSector().categoria();
    }
}
