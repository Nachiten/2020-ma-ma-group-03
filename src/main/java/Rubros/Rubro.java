package Rubros;

import Categoria.TipoEmpresa;

public class Rubro {

    public int promedioVentasAnuales;
    public int cantPersonal;

    public TipoEmpresa clasificacion(){
        return null;
    }

    protected boolean cumpleCondicionCon(int ventasAnuales, int cantPersonal){
        return this.promedioVentasAnuales <= ventasAnuales && this.cantPersonal <= cantPersonal;
    }
}