package TipoEntidadJuridica;

import Entidades.Afip;

public class Empresa extends TipoEntidadJuridica {

    public Sector sector;

    private String categoria(){
        return Afip.clasificacion(this);
    }

    public Sector getSector() {
        return sector;
    }

}