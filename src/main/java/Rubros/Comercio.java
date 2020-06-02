package Rubros;

import Categoria.TipoEmpresa;

public class Comercio extends EstrategiaDeRubro {

    @Override
    public TipoEmpresa clasificacion() {
        return tipoDeEmpresa(this.promedioVentasAnuales, this.cantPersonal);
    }

    @Override
    public int tipoDeEmpresaPorVentasAnuales(int promedioVentasAnuales){

        TipoEmpresa tipoEmpresa = null;

        if(promedioVentasAnuales <= 29740000)
            tipoEmpresa = TipoEmpresa.MICRO;
        if(promedioVentasAnuales <= 178860000)
            tipoEmpresa = TipoEmpresa.PEQUENA;
        if(promedioVentasAnuales <= 1502750000)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        if(promedioVentasAnuales <= 214681000)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;

        return tipoEmpresa.ordinal();
    }


    @Override
    public int tipoDeEmpresaPorCantidadPersonal(int cantidadPersonal){

        TipoEmpresa tipoEmpresa = null;

        if(promedioVentasAnuales <= 7)
            tipoEmpresa = TipoEmpresa.MICRO;
        if(promedioVentasAnuales <= 35)
            tipoEmpresa = TipoEmpresa.PEQUENA;
        if(promedioVentasAnuales <= 125)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        if(promedioVentasAnuales <= 345)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;

        return tipoEmpresa.ordinal();
    }
}
