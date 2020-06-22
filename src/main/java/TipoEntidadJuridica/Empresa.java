package TipoEntidadJuridica;

import Entidades.Afip;

public class Empresa extends TipoEntidadJuridica {

    public Sector sector;

    //Agregué el constructor
    public Empresa(Sector sector) {
        this.sector = sector;
    }

    private Categoria categoria(){
        return Afip.clasificacion(this);
    }

    public Sector getSector() {
        return sector;
    }
}