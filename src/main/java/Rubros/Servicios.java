package Rubros;

import Categoria.TipoEmpresa;

public class Servicios extends EstrategiaDeRubro {

    @Override
    public TipoEmpresa clasificacion() {
        return tipoDeEmpresa(this.promedioVentasAnuales, this.cantPersonal);
    }

    @Override
    public int tipoDeEmpresaPorVentasAnuales(int promedioVentasAnuales){

        TipoEmpresa tipoEmpresa = null;

        if(promedioVentasAnuales <= 8500000)
            tipoEmpresa = TipoEmpresa.MICRO;
        if(promedioVentasAnuales <= 50950000)
            tipoEmpresa = TipoEmpresa.PEQUENA;
        if(promedioVentasAnuales <= 425170000)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        if(promedioVentasAnuales <= 607210000)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;

        return tipoEmpresa.ordinal();
    }


    @Override
    public int tipoDeEmpresaPorCantidadPersonal(int cantidadPersonal){

        TipoEmpresa tipoEmpresa = null;

        if(promedioVentasAnuales <= 7)
            tipoEmpresa = TipoEmpresa.MICRO;
        if(promedioVentasAnuales <= 30)
            tipoEmpresa = TipoEmpresa.PEQUENA;
        if(promedioVentasAnuales <= 165)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        if(promedioVentasAnuales <= 535)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;

        return tipoEmpresa.ordinal();
    }
}