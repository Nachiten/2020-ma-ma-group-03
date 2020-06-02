package Rubros;

import Categoria.TipoEmpresa;

public class Agropecuario extends EstrategiaDeRubro {

    @Override
    public TipoEmpresa clasificacion() {
        return tipoDeEmpresa(this.promedioVentasAnuales, this.cantPersonal);
    }

    @Override
    public int tipoDeEmpresaPorVentasAnuales(int promedioVentasAnuales){

        TipoEmpresa tipoEmpresa = null;

        if(promedioVentasAnuales <= 12890000)
            tipoEmpresa = TipoEmpresa.MICRO;
        if(promedioVentasAnuales <= 48480000)
            tipoEmpresa = TipoEmpresa.PEQUENA;
        if(promedioVentasAnuales <= 345430000)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        if(promedioVentasAnuales <= 547890000)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;

        return tipoEmpresa.ordinal();
    }


    @Override
    public int tipoDeEmpresaPorCantidadPersonal(int cantidadPersonal){

        TipoEmpresa tipoEmpresa = null;

        if(promedioVentasAnuales <= 5)
            tipoEmpresa = TipoEmpresa.MICRO;
        if(promedioVentasAnuales <= 10)
            tipoEmpresa = TipoEmpresa.PEQUENA;
        if(promedioVentasAnuales <= 50)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        if(promedioVentasAnuales <= 215)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;

        return tipoEmpresa.ordinal();
    }
}