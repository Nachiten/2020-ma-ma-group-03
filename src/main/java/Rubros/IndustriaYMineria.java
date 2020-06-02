package Rubros;

import Categoria.TipoEmpresa;

public class IndustriaYMineria extends EstrategiaDeRubro {

    @Override
    public TipoEmpresa clasificacion() {
        return tipoDeEmpresa(this.promedioVentasAnuales, this.cantPersonal);
    }

   @Override
    public int tipoDeEmpresaPorVentasAnuales(int promedioVentasAnuales){

        TipoEmpresa tipoEmpresa = null;

        if(promedioVentasAnuales <= 26540000)
            tipoEmpresa = TipoEmpresa.MICRO;
        if(promedioVentasAnuales <= 190410000)
            tipoEmpresa = TipoEmpresa.PEQUENA;
        if(promedioVentasAnuales <= 1190330000)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        if(promedioVentasAnuales <= 1739590000)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;

        return tipoEmpresa.ordinal();
    }


    @Override
    public int tipoDeEmpresaPorCantidadPersonal(int cantidadPersonal){

        TipoEmpresa tipoEmpresa = null;

        if(promedioVentasAnuales <= 15)
            tipoEmpresa = TipoEmpresa.MICRO;
        if(promedioVentasAnuales <= 60)
            tipoEmpresa = TipoEmpresa.PEQUENA;
        if(promedioVentasAnuales <= 235)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        if(promedioVentasAnuales <= 655)
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;

        return tipoEmpresa.ordinal();
    }
}


